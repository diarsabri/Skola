#include "helper.h"
#include <stdlib.h>

//Initialization of variables
unsigned int tick2 = 0;
int led2 = 1;
int led_counter = 0;
int consumable_present = 0;
int tail_x[MAX_LENGTH] = {0};
int tail_y[MAX_LENGTH] = {0};
int cur_length = START_LENGTH;
enum Mode mode;

Snake snake;

//Restart-function
void restart(void) {
    update_display(pixels); 
    update_display(pixels);
    delay(3000000);
    update_display(pixels);
}

//Draws the borders and marks them as ACTIVE
void draw_borders(void) {
    int k,l;
    for (k = 0;k<128;k++) {
        draw_pixel(k,0);
        draw_pixel(k,31);
        coordinates[k][0]    = ACTIVE;
        coordinates[k][31]   = ACTIVE;
    }

    for (l=0;l<31;l++) {
        draw_pixel(0,l);
        draw_pixel(127,l);
        coordinates[0][l]    = ACTIVE;
        coordinates[127][l]  = ACTIVE;
    }
}

//Changes the snakes direction based on the heads direction
void snake_direction(Snake *snake, int sw1, int sw2) {

    if (sw1 == 1) {
        if (((*snake).direction == UP) | ((*snake).direction == DOWN)) {
            (*snake).direction = RIGHT;
        }
    } else if ((sw1 >> 1) == 1) {
        if (((*snake).direction == UP) | ((*snake).direction == DOWN)) {
            (*snake).direction = LEFT;
        }
    } else if (sw2 == 1) {
        if (((*snake).direction == LEFT) | ((*snake).direction == RIGHT)) {
            (*snake).direction = UP;
        }
    } else if ((sw2 >>1) == 1) {
        if (((*snake).direction == LEFT) | ((*snake).direction == RIGHT)) {
            (*snake).direction = DOWN;
        }
    }

}

//Function for actually moving the snake based on the current snake-head direction

void slither(Snake *snake) {
    int u, q,x,y,z;

    x = (*snake).x;
    y = (*snake).y;
    u = (*snake).length;

    switch((*snake).direction) {
        case RIGHT:
        {
            x++;
        } break;
        case LEFT:
        {
            x--;
        } break;
        case UP:
        {
            y++;
        } break;
        case DOWN:
        {
            y--;
        } break;
    }

    (*snake).x = x; (*snake).y = y;
    coordinates[0][0] = ACTIVE;
    
    int previous_x = tail_x[0];
    int previous_y = tail_y[0];
    int previous_2_x, previous_2_y;

    tail_x[0] = x;
    tail_y[0] = y;

    for (z=0;z<=cur_length;z++) {
        previous_2_x = tail_x[z];
        previous_2_y = tail_y[z];
        tail_x[z] = previous_x;
        tail_y[z] = previous_y;
        previous_x = previous_2_x;
        previous_y = previous_2_y;
        draw_pixel(tail_x[z],tail_y[z]);
        if (z > 1) {
            coordinates[tail_x[z]][tail_y[z]] = ACTIVE;
        }
    }
    erase_pixel(tail_x[cur_length],tail_y[cur_length]);
    coordinates[tail_x[cur_length]][tail_y[cur_length]] = INACTIVE;    
}

//snake-reset function for when restarting the game
void snake_reset(Snake *snake) {
    int i;
    (*snake).x = 63;
    (*snake).y = 15;
    (*snake).direction = LEFT;
    (*snake).length = START_LENGTH;
    for (i = 0;i<cur_length;i++) {
        tail_x[i] = 0;
        tail_y[i] = 0;
        erase_pixel(tail_x[i],tail_y[i]);
    }
}

//random int generator, taken from https://stackoverflow.com/questions/1167253/implementation-of-rand
//couldn't get the rand() function from the stdlib to work
unsigned int lfsr113_Bits (void)
{
   static unsigned int z1 = 12345, z2 = 12345, z3 = 12345, z4 = 12345;
   unsigned int b;
   b  = ((z1 << 6) ^ z1) >> 13;
   z1 = ((z1 & 4294967294U) << 18) ^ b;
   b  = ((z2 << 2) ^ z2) >> 27; 
   z2 = ((z2 & 4294967288U) << 2) ^ b;
   b  = ((z3 << 13) ^ z3) >> 21;
   z3 = ((z3 & 4294967280U) << 7) ^ b;
   b  = ((z4 << 3) ^ z4) >> 12;
   z4 = ((z4 & 4294967168U) << 13) ^ b;
   return (z1 ^ z2 ^ z3 ^ z4);
}

//randomly generates food at certain coordinates
void generate_food(void) {
    int rand_x, rand_y;


    rand_x = lfsr113_Bits() % 127;
    rand_y = lfsr113_Bits() % 30;

    draw_pixel(rand_x,rand_y);
    consumable_present = 1;

    coordinates[rand_x][rand_y] = CONSUMABLE;

}


//fetare orm
//olika banor
//snabbare med tiden
//räkna poäng /antal poäng



//main in-game function
//starts by resetting and restarting the game etc
void play(void) {    
    restart();
    draw_borders();
    update_display(pixels);
    snake_reset(&snake);

    int timer_delay = 0;
    while (1) {
        ticker();
        if(mode != GAME) return;

        int sw1 = SWITCHES & 3;             //switch 1,2
        int sw2 = (SWITCHES >> 2) & 3;      //switch 3,4
        snake_direction(&snake,sw1,sw2);    //sets the snakes direction
        
        if (!TIMER) continue;               //timer-function
        RESTART_TIMER;

        PORTE = lfsr113_Bits() % 256;       //randomly set the leds in-game

        if (coordinates[snake.x][snake.y] == ACTIVE) {  //reset function
            mode = REPLAY;
            clear_screen();
            snake_reset(&snake);
            delay(1000);
            draw_borders();
            consumable_present = 0;
            return;
        }
        if (coordinates[snake.x][snake.y] == CONSUMABLE) {  //growth-function
            snake.length++;
            cur_length++;
            consumable_present = 0;
        }

        update_display(pixels);
        slither(&snake);                                    //draw
        if (consumable_present == 0) {                      //when food has been eaten, generate new
            generate_food();
        }
        update_display(pixels);
        
    }
}