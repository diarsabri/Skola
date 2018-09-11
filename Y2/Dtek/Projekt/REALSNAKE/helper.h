#ifndef HELPER_H
#define HELPER_H

#include <stdint.h>
#include <pic32mx.h>

#define CONSUMABLE          2
#define ACTIVE              1
#define INACTIVE            0
#define SWITCHES            ((PORTD >> 8) & 0xF)
#define BUTTON_PRESSED(x)   (get_buttons() >> x) & 1
#define RESTART_TIMER       IFS(0) &= ~(0x1 << 8)
#define TIMER               ((IFS(0) >> 8) & 0x1)
#define START_LENGTH        8
#define MAX_LENGTH          400

enum Direction {LEFT, RIGHT, UP, DOWN};
enum Mode {LAUNCH, GAME, REPLAY};

typedef struct {
    int x, y, length;
    enum Direction direction;
} Snake;

extern uint8_t pixels[512];
extern uint8_t coordinates[128][32];
extern uint8_t snake_body[128][32];
extern unsigned int tick1;
extern unsigned int tick2;

extern uint8_t launch_screen[512];
extern uint8_t launch_screen_4[512];
extern uint8_t replay_screen[512];
extern uint8_t replay_screen_4[512];

extern enum Mode mode;
extern enum Direction direction;
extern Snake snake;

void draw_pixel(int x,int y);
void erase_pixel(int x,int y);
void restart(void);
void play(void);
void draw_snake();
void slither();
void start_display();
void update_display(uint8_t *pix);

#endif