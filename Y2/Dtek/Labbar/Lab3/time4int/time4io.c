#include <stdint.h>
#include <pic32mx.h>
#include "mipslab.h"

int getsw( void )
{
  int value = (PORTD >> 8) & 0xf;
  return value;
}

int getbtns( void )
{
  int value = (PORTD >> 5) & 0x7;
  return value;
}
