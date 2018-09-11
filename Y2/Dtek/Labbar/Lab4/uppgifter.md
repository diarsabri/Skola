seminarie 2 - dtek

Uppgift 1:
Rad 7 AND:

Jump        = 0         Ingen Jump
RegWrite    = 1         Vi vill läsa, inte skriva
RegDst      = 1         Vi vill ta 15:11, inte 20:16
ALUSrc      = 0         Ingen jump instruktion
ALUControl  = 000       F = And = 000
Branch      = 0         Det ska inte branchas
MemWrite    = 0         Vi vill inte skriva till minnet
MemToReg    = 0         Vi vill inte läsa från minnet

Rad 8 SW:

Jump        = 0         Ingen jump
RegWrite    = 0         
RegDst      = ?
ALUSrc      = 1
ALUControl  = 010
Branch      = 0
MemWrite    = 1
MemToReg    = ?

Rad 11 J:

Jump        = 1
RegWrite    = 0
RegDst      = ?
ALUSrc      = ?
ALUControl  = ?
Branch      = ?
MemWrite    = 0
MemToReg    = ?