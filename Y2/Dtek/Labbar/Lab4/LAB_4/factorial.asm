addi $a0, $0, 4 #a0 = n
beq $a0, $0, zero
addi $a1, $a0, -1
add $a2, $0, $0
add $a3, $0, $0

loop:	
	beq $a1, $0, finished
	add $a2, $0, $a1

	multiply: 	
		beq $a2, $0, endMult
		add $a3, $a3, $a0
		addi $a2, $a2, -1
		beq $0, $0, multiply
	
endMult:
	add $a0, $0, $a3
	add $a3, $0, $0
	addi $a1, $a1, -1
	
	add $v0, $0, $a0
	beq $0, $0, loop
	
zero: 
	addi $v0, $0, 1
	
finished:	
	beq $0, $0, finished
