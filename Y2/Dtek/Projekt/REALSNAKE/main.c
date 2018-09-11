#include "helper.h"

uint8_t pixels[512];
uint8_t coordinates[128][32];
unsigned int tick1 = 0;
int led = 0xf;


enum Mode mode;

void start_program(void) {
    /*
    TAKEN FROM LAB 3
    This will set the peripheral bus clock to the same frequency
    as the sysclock. That means 80 MHz, when the microcontroller
    is running at 80 MHz. Changed 2017, as recommended by Axel.
    */
    SYSKEY = 0xAA996655;       /* Unlock OSCCON, step 1 */
    SYSKEY = 0x556699AA;       /* Unlock OSCCON, step 2 */
    while(OSCCON & (1 << 21)); /* Wait until PBDIV ready */
    OSCCONCLR = 0x180000;      /* clear PBDIV bit <0,1> */
    while(OSCCON & (1 << 21)); /* Wait until PBDIV ready */
    SYSKEY = 0x0;              /* Lock OSCCON */

    /* Set up output pins */
    AD1PCFG = 0xFFFF;
    ODCE = 0x0;
    TRISECLR = 0xFF;
    PORTE = 0x0;

    /* Output pins for display signals */
    PORTF = 0xFFFF;
    PORTG = (1 << 9);
    ODCF = 0x0;
    ODCG = 0x0;
    TRISFCLR = 0x70;
    TRISGCLR = 0x200;

    /* Set up input pins */
    TRISDSET = (1 << 8);
    TRISFSET = (1 << 1);

    /* Set up SPI as master */
    SPI2CON = 0;
    SPI2BRG = 4;
    /* SPI2STAT bit SPIROV = 0; */
    SPI2STATCLR = 0x40;
    /* SPI2CON bit CKP = 1; */
    SPI2CONSET = 0x40;
    /* SPI2CON bit MSTEN = 1; */
    SPI2CONSET = 0x20;
    /* SPI2CON bit ON = 1; */
    SPI2CONSET = 0x8000;
}

//Starts the timer, the same idea from lab 3
void start_timer(void) {
    PR2 = 31250*1.2;
    TMR2 = 0;
    T2CON = 0x8070;
}

//Ticker that lets us access the timer
void ticker(void) {
    if (IFS(0) & 0x100) {
    tick1++;
    tick2++;
    }
}

//Gets all the buttons
int get_buttons(void) {
    int button_1 = (PORTF >> 1) & 1;            //port f bit 1 is button 1
    return ((PORTD >> 4) & 0xE) | button_1;     //buttons 2-4
}

//Draws a pixel on the 128*32 grid
void draw_pixel(int x, int y) {
    int row = 0;
    if (y>0) {
        row = y/8;
    }
    pixels[row * 128 + x] |= 1 << (y - row * 8);
}

//Erase a pixel, same logic
void erase_pixel(int x, int y) {
    int row = 0;
    if (y>0) {
        row = y/8;
    }
    pixels[row * 128 + x] &= ~( 1 << (y - row * 8));
}

//Clears the screen by filling the pixel-array with zero's 
//Also marks all cells as INACTIVE
void clear_screen(void) {
    int i,j,k;
    update_display(pixels);

    for (i = 0; i < 512; ++i) {
        pixels[i] = 0;
    }

    for (j = 0;j<127;j++) {
        for (k = 0;k<31;k++) {
            coordinates[j][k] = INACTIVE;
            erase_pixel(j,k);
        }
    }

    update_display(pixels);
}

//The launcher and its buttons, arrays and led's.
void launch(void) {
    while (1) {
        ticker();
        if (BUTTON_PRESSED(3)) mode = GAME;
        if (mode != LAUNCH) return;

        if (!TIMER) continue;
        RESTART_TIMER;

        if (IFS(0) & 0x100) {
            tick2++;
        }

        if (tick2 == 1) {
            update_display(launch_screen);
            PORTE &= ~0xFF;
            PORTE |= led;
        } else {
            update_display(launch_screen_4);
            PORTE &= ~0xFF;
            PORTE |= ~led;
            tick2 = 0;
        }
    }
}

//The game-over screen and its buttons, arrays and led's.
void replay(void) {
    while (1) {
        ticker();
        if (BUTTON_PRESSED(3)) mode = GAME;
        if (mode!= REPLAY) return;

        if (!TIMER) continue;
        RESTART_TIMER;

        if (IFS(0) & 0x100) {
            tick2++;
        }

        if (tick2 == 1) {
            update_display(replay_screen);
            PORTE &= ~0xFF;
            PORTE |= led;
        } else {
            update_display(replay_screen_4);
            PORTE &= ~0xFF;
            PORTE |= ~led;
            tick2 = 0;
        }
    }
}

//Main-function. Basically runs the corresponding code for each mode.
int main(void) {
    start_program();
    start_timer();
    start_display();

    update_display(launch_screen);

    mode = LAUNCH;

    while(1) {
        switch(mode) {
            case LAUNCH:    launch();   break;
            case GAME:      play();     break;
            case REPLAY:    replay();   break;
        }
    }
}