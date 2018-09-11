/* mipslabwork.c

   This file written 2015 by F Lundevall
   Updated 2017-04-21 by F Lundevall

   This file should be changed by YOU! So you must
   add comment(s) here with your name(s) and date(s):

   This file modified 2017-04-31 by Ture Teknolog

   For copyright and licensing, see file COPYING */

#include <stdint.h>   /* Declarations of uint_32 and the like */
#include <pic32mx.h>  /* Declarations of system-specific addresses etc */
#include "mipslab.h"  /* Declatations for these labs */

int mytime = 0x5957;
int timeoutcount = 0;
int prime = 1234567;
int switch1 = 0;

volatile int* portE = (volatile int*) 0xbf886110;

char textstring[] = "text, more text, and even more text!";

/* Interrupt Service Routine */
void user_isr( void )
{

  if(IFS(0) & 0x80){
    *portE = *portE + 0x1;
    IFSCLR(0) = 0x80;
  }

  if(IFS(0) & 0x100){
    timeoutcount++;

    //IFS(0) = 0;
    IFSCLR(0) = 0x100;
  }

  if(timeoutcount == 10){
    time2string( textstring, mytime );
    display_string( 3, textstring );
    display_update();
    tick( &mytime );
    timeoutcount = 0;
  }


  //display_image(96, icon);

}

/* Lab-specific initialization goes here */
void labinit( void )
{
  //we put trise to the address of port E
  //and then we change the least significant 8 bits to 0, that is output. 1 is input

  volatile int* trise = (volatile int*) 0xbf886100;
  *trise = *trise & 0xFF00;
  TRISD &= 0Xfe0;

  *portE = 0x0;


  T2CON = 0X0;    //CLEAR & STOP CONTROL REGISTER
  PR2 = 31250;
  T2CONSET = 0x70;
  T2CONSET = 0x8000;

  IPC(1) = 0x1c << 24;
  IPC(2) = 7;
  IEC(0) = 0x100;
  IEC(0) |= 1 << 7;

  enable_interrupt();

  return;
}

/* This function is called repetitively from the main program */
void labwork( void )
{
  prime = nextprime( prime );
  display_string( 0, itoaconv( prime ) );
  display_update();
}
