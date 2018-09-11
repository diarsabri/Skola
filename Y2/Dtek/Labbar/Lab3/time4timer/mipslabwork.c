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

char textstring[] = "text, more text, and even more text!";

/* Interrupt Service Routine */
void user_isr( void )
{
  return;
}

/* Lab-specific initialization goes here */
void labinit( void )
{
  //we put trise to the address of port E
  //and then we change the least significant 8 bits to 0, that is output. 1 is input
  volatile int* trise = (volatile int*) 0xbf886100;
  *trise = *trise & 0xFF00;
  TRISD &= 0Xfe0;

  PR2 = 31250;  //Set the period
  T2CONSET = 0x70;  //Set the prescalar, bits 6-4 to 111 (the prescale-value 256 for type )
  T2CONSET = 0x8000;  //Start the timer


  return;
}

/* This function is called repetitively from the main program */
void labwork( void )
{
  //initialize portE to port E
  //set the VALUE to zero initally
  volatile int* portE = (volatile int*) 0xbf886110;
  *portE = 0x0;

  int switches;
  int buttons;

  while(1) {
  switches = getsw();
  buttons = getbtns();

  if((buttons & 0x1) == 1){
    mytime = mytime & 0xff0f;
    mytime = (switches << 4) | mytime;
  }
  if(((buttons >> 1) & 0x1) == 1){
    mytime = mytime & 0xf0ff;
    mytime = (switches << 8) | mytime;
  }
  if(((buttons >> 2) & 0x1) == 1){
    mytime = mytime & 0x0fff;
    mytime = (switches << 12) | mytime;
  }

  if(IFS(0) & 0x100){
    timeoutcount++;
    IFS(0) = 0;
    //IFSCLR(0) = 0x100;
  }

  if(timeoutcount == 10){
    time2string( textstring, mytime );
    display_string( 3, textstring );
    display_update();
    tick( &mytime );
    *portE = *portE + 0x1;

    timeoutcount = 0;
  }
  display_image(96, icon);
  }

}
