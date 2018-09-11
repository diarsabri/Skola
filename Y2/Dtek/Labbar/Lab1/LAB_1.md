# Lab1 - Assembly Programming

### Assignment 1 - Questions

Changing
```addi	$s0,$s0,1``` to ```addi	$s0,$s0,3```, on line 16 as well as adjusting the loop
condition on line 19, ```0x5b``` to ```0x5d``` does the trick. (so it doesn't loop endlessly)

### Assignment 2 - Questions
* No S-registers were used.

* The return value is 1, this is because 17 in binary is 10001, if me mask everything except the 4 least significant digits, 0001 is all that remains, which equals 1 in base 10.

* For input values below 10, we branch to another location.

### Assignment 3 - Questions
* **$ra** is saved because its contents are changed each time the **hexasc** subroutine is called, it is then restored at the end of the **time2string** function. Same goes for **$a0** since it's the register used as the argument for the **hexasc** subroutine. We also save each return value from the **hexasc** function by pushing it into a stack and then pop the values out of the stack and assign these to **$t0-$t3** as soon as there are no more **hexasc** calls, this is because we generally assume that we do not know which registers are used within a subroutine and thus we make sure to store values instead of changing them to avoid conflicts.

* **$t4** and **$t5** are used but not restored, this is because they're used at the end of **time2string* when all the **hexasc** calls have been made and thus there's no risk in changing them.

* line 134 - 136. Null everything but the 5 from **$a1** with andi, then move it to the right.  
```
andi $a0, $a1, 0x000000F0
srl $a0, $a0, 4
jal hexasc```

* If the argument **$a0** is 0 (or less), the compiler will jump directly to the end subroutine since the branch condition, ```ble $a0, 0, end```, asking if **$a0** is less than or equal to zero.








































































#########################
andi $t6, $a1, 0x0000FFFF
beq $t6,0,DINGER
nop
#########################












###################
DINGER:
li $t0,0x44
li $t1,0x49
li $t2,0x4e
li $t3,0x47
li $t4,0x00


sb $t0, 0($a0)
sb $t1, 1($a0)
sb $t2, 2($a0)
sb $t3, 3($a0)
sb $t4, 4($a0)

j finito
nop
