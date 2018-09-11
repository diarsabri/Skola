# **LAB 4 - Processor Design**

### Assignment 1 - questions

#### Question 1
* **A AND B** - the two input values A and B are unchanged (F<sub>2</sub> = 0) and the output from the AND gate is selected as output F<sub>0,1</sub> = 00

* **A OR B** - the same as above, except the output from the OR gate is selected as output F<sub>0,1</sub> = 01
* **A + B** - once again the same as above but the adders direct output is selected F<sub>0,1</sub>=10
* **A and !B** - works the same as the first AND operation, but now B is inverted F<sub>2</sub> = 1
* **A OR !B** - works the same as the first OR operation, but now B is inverted F<sub>2</sub> = 1
* **A-B** - the same output as for the A+B operation is selected, but this time F<sub>2</sub> = 1 which means that both B's value will be inverted but also incremented by 1 (with the help of the carry in that F<sub>2</sub> is also connected to) and thus we get 2's complement.
* **SLT** - works similarly to A-B except a different output is selected, F<sub>0,1</sub> = 11. The most significant bit is extracted and if it is a 1 (signed) it means that A is less than B since the resulting value is negative.  

#### Question 2
 To signalize that the output from the 4:1 multiplexer is 0, we used a comparator to make sure that the output value is equal to a constant 0. We tried using an implementation with a NOR gate that outputs only 1's if the output and a constant is zero, but this turned out to be a really messy approach as we had to split 32 bits into single bits with a splitter and then connect them to an AND gate to get the answer we were interested in.

 #### Question 3
This is because you can use the same hardware for multiple operations.

### Assignment 2 - questions

#### Question 1
Only the write operation is updated on the rising edge of the clock, this is so that we data isn't written and read simultaneously. Data should be accessible all the time but only written to on the rising edge.

#### Question 2
Reading from **$0** will always result in the value 0, because the $zero register is always constant. Writing to the $zero register is not possible.
We implemented this behavior by adding a constant input to the 0 address.

#### Question 3
This register can only store 8*32 = 256 bits of information (counting the $zero register). For a complete 32-bits MIPS processor, the value is 1024.

### Assignment 3
#### Question 1
We connected a comparator that outputs true if the OP-code inserted equals 4, i.e the beq instruction.
We assume our solution is correct because RegDst, RegWrite, AluSrc, Branch & ALUControl all have the correct outputs for three operations.

#### Question 2
The ADD signal requires RegDst to be high (1) because we want to write data to the the register address stored in bits 15 to 11. It also needs ALUSrc to be low because we want to send the register's data to the adder and not the last 15 bits.
The ADDI signal on the other hand needs writes to the data of the register stored in bits 16 to 11 and also needs the last 15 bits to be forwarded to the ALU.

### Assignment 4
#### Question 1
For the inputs to the RegDst multiplexer we used the bit selection to divide the 32bit string into groups of 3 bits each, then we selected the group that had the bits we were interested in. If any bit was just outside a group we left or right shifted all the bits first. **ADD** needs the RegDst multiplexer to have 0 as selected output as it will add the value of two registers together, where as **ADDI** will add an arbitrary value with a register's data and therefore needs the RegDst multiplexer to be high, since that's the wire with the last 16 bits.

#### Question 2
The beq instruction, just like addi, needs the last 16 bits of the instruction, the label, to figure out how many instructions it should jump if a condition is met. Therefore it is connected to the same wire as addi but branches into another where we left shift the sign extended bits, because the address always has to end with two zeros. It is then incremented by 4 and sent back to the PC and do so for as long as the condition isn't met, i.e, loop. If the condition is met, that is, if the ALU value is zero, the branch wire will be high and therefore the looping will stop and the program will continue as usual.

### Assignment 5
#### Question 1
For us to create the factorial function in MIPS Assembly code, we first created a
working function in C code which does what we want. Basically we we create a
multiplication loop, which has the same functionality as a simple multiplication.
This is the inner loop, and the outer loop starts with n-1 and multiplies with
itself all the way down to n = 1.

#### Question 2
We handled jumps in assembly by using the beq instruction. We made an infinity
loop by branching if zero = zero and jumping to the start again.
