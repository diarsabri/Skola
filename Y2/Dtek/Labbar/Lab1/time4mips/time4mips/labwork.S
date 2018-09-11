	# labwork.S
  # Written 2015-2017 by F Lundevall
  # Skeleton file for IS1200/IS1500 lab 1.
  # The macros PUSH and POP are in the public domain.
  # Please add your own code at the end of the file.

  #
  # Please keep the two macros PUSH and POP unchanged
  #
.macro	PUSH reg
	addi	$sp,$sp,-4
	sw	\reg,0($sp)
.endm

.macro	POP reg
	lw	\reg,0($sp)
	addi	$sp,$sp,4
.endm
  #
  # Please add your own code below this line
  #

	.data
	.text
	.global delay
	.global time2string

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

  delay:


		# variables for storing counter and the constant which is supposed to be
		# easy to change

  	li $t0, 0 #counter for for-loop
  	li $t1, 4350000 # constant

  	# while-loop that branches to end if $a0 == 0
		#
  	whileloop:
  		ble  $a0, 0, finish
  		nop
  		addi $a0,$a0,-1

  		forloop:
  			beq $t0, $t1, whileloop
  			nop
  			addi $t0, $t0, 1

  			j forloop
  			nop

  	finish:
  		jr $ra
  		nop

  time2string:
  	#saves the address and the return value
  	PUSH $ra
  	PUSH $a0

		# DINGER
		andi $t6, $a1, 0x000000FF
		beq $t6,0,DINGER
		nop

  	#extract 4th digit from $a1 by masking it and then move it 3 positions to the right
  	andi $a0, $a1, 0x0000F000
  	srl $a0, $a0, 12
  	jal hexasc
  	nop

  	PUSH $v0


  	#repeat above step for the following three digits
  	andi $a0, $a1, 0x00000F00
  	srl $a0, $a0, 8
  	jal hexasc
  	nop

  	PUSH $v0

  	andi $a0, $a1, 0x000000F0
  	srl $a0, $a0, 4
  	jal hexasc
  	nop

  	PUSH $v0

  	andi $a0, $a1, 0x0000000F
  	jal hexasc
  	nop

  	PUSH $v0

  	#add colon and null
  	addi $t4, $0, 0x3a
  	addi $t5, $0, 0x00

  	POP $t3
  	POP $t2
  	POP $t1
  	POP $t0


  	#$a0 recieves the link to the address again
  	POP $a0


  	sb $t0, 0($a0)
  	sb $t1, 1($a0)
  	sb $t4, 2($a0)
  	sb $t2, 3($a0)
  	sb $t3, 4($a0)
  	sb $t5, 5($a0)

  	#get the old return value back
  	POP $ra

		finito:
  	jr $ra
  	nop

		DINGER:

		li $t0,0x42
		li $t1,0x4f
		li $t2,0x4f
		li $t3,0x4d
		li $t4,0x00

		sb $t0, 0($a0)
		sb $t1, 1($a0)
		sb $t2, 2($a0)
		sb $t3, 3($a0)
		sb $t4, 4($a0)

		j finito
		nop
