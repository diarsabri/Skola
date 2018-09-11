  # hexmain.asm
  # Written 2015-09-04 by F Lundevall
  # Copyright abandonded - this file is in the public domain.

	.text
main:
	li	$a0, 14		# change this to test different values

	jal	hexasc		# call hexasc
	nop			# delay slot filler (just in case)	

	move	$a0,$v0		# copy return value to argument register

	li	$v0,11		# syscall with v0 = 11 will print out
	syscall			# one byte from a0 to the Run I/O window
	
stop:	j	stop		# stop after one run
	nop			# delay slot filler (just in case)

  # You can write your own code for hexasc here
  #
  
hexasc:	
	li $t0, 0x0000000F
	and $t2, $a0, $t0
	
	slti $t1, $t2, 10 
	beq $t1,$0, overTen
	nop
	addi $v0, $t2, 48
	j ret
	nop
	
	overTen: 
		addi $v0, $t2, 55
	
	ret:
		jr $ra
		nop
	
	
	
	
	#li $t0, 0xA
	#slt $t1,$t0,$a0      # checks if $t0 > $t2
	#beq $t1,1,belowten     # if $t0 > $t2, goes to belowten
	#belowten:
	
