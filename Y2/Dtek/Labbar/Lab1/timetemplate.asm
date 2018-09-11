  # timetemplate.asm
  # Written 2015 by F Lundevall
  # Copyright abandonded - this file is in the public domain.

.macro	PUSH (%reg)
	addi	$sp,$sp,-4
	sw	%reg,0($sp)
.end_macro

.macro	POP (%reg)
	lw	%reg,0($sp)
	addi	$sp,$sp,4
.end_macro

	.data
	.align 2
mytime:	.word 0x5957
timstr:	.ascii "text more text lots of text\0"
	.text
main:
	# print timstr
	la	$a0,timstr
	li	$v0,4
	syscall
	nop
	# wait a little
	li	$a0,1000
	jal	delay
	nop
	# call tick
	la	$a0,mytime
	jal	tick
	nop
	# call your function time2string
	la	$a0,timstr
	la	$t0,mytime
	lw	$a1,0($t0)
	jal	time2string
	nop
	# print a newline
	li	$a0,10
	li	$v0,11
	syscall
	nop
	# go back and do it all again
	j	main
	nop
	
# tick: update time pointed to by $a0
tick:	lw	$t0,0($a0)	# get time
	addiu	$t0,$t0,1	# increase
	andi	$t1,$t0,0xf	# check lowest digit
	sltiu	$t2,$t1,0xa	# if digit < a, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0x6	# adjust lowest digit
	andi	$t1,$t0,0xf0	# check next digit
	sltiu	$t2,$t1,0x60	# if digit < 6, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0xa0	# adjust digit
	andi	$t1,$t0,0xf00	# check minute digit
	sltiu	$t2,$t1,0xa00	# if digit < a, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0x600	# adjust digit
	andi	$t1,$t0,0xf000	# check last digit
	sltiu	$t2,$t1,0x6000	# if digit < 6, okay
	bnez	$t2,tiend
	nop
	addiu	$t0,$t0,0xa000	# adjust last digit
tiend:	sw	$t0,0($a0)	# save updated result
	jr	$ra		# return
	nop

  # you can write your code for subroutine "hexasc" below this line
  #

hexasc:	
	li $t0, 0x0000000F
	#hexadecimalt sparar vi den sista siffran
	and $t2, $a0, $t0
	#vi andar med argumentet för att extrahera sista siffran
	
	slti $t1, $t2, 10 
	#om t1 < 10 returnera 1, annars returnera 0
	beq $t1,$0, overTen
	#om t1 > 10 (0) , hoppa till overTen, annars kör nedanstående
		nop
		addi $v0, $t2, 48
		#lägger till 48 för att få första siffran
		j ret
		#hoppa över overTen till ret
		nop
	
	overTen: 
		addi $v0, $t2, 55
		#lägger till 55 för att få första bokstaven
	
	ret:
		jr $ra
		nop

  delay:


	# variables for storing counter and the constant which is supposed to be
	# easy to change

  	li $t0, 0 #counter for for-loop
  	li $t1, 1000 # constant

  	# while-loop that branches to end if $a0 == 0
	
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
	PUSH ($ra) 
	PUSH ($a0)
	PUSH ($s1)
	
	#DINGER
	andi $t6, $a1, 0x000000FF
	beq $t6,0,DINGER
	
	move $s1, $a1
		
	#extract 4th digit from $a1 by masking it and then move it 3 positions to the right
	andi $a0, $s1, 0x0000F000
	srl $a0, $a0, 12
	jal hexasc
	nop

	PUSH ($v0)
	
	
	#repeat above step for the following three digits
	andi $a0, $s1, 0x00000F00
	srl $a0, $a0, 8
	jal hexasc
	nop

	PUSH ($v0)
	
	andi $a0, $s1, 0x000000F0
	srl $a0, $a0, 4
	jal hexasc
	nop

	PUSH ($v0)
	
	andi $a0, $s1, 0x0000000F
	jal hexasc
	nop

	PUSH ($v0)
	
	#add colon and null
	addi $t4, $0, 0x3a
	addi $t5, $0, 0x00
	
	POP ($t3)
	POP ($t2)
	POP ($t1)
	POP ($t0)
	
	
	#$a0 recieves the link to the address again
	POP ($s1)
	POP ($a0)
	
	
	sb $t0, 0($a0)
	sb $t1, 1($a0)
	sb $t4, 2($a0)
	sb $t2, 3($a0)
	sb $t3, 4($a0)
	sb $t5, 5($a0)
	
	#get the old return value back
	POP ($ra)
	
		finito:
  	jr $ra
  	nop

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
