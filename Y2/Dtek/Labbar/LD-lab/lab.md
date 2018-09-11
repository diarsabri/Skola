### Lab-LD

**2.1**

The wire is blue because it is an unknown one-bit value. This depends on the enabler, if we set the enabler to 1 the wire will be dark-green indicating output = zero.

The equal signal is red because it is indicating an error, this is because of conflicting values.

The select wire is black since it is carrying multiple values, of which some may or may not be specified.

**2.2**

**2.2.1**

[Uppgift 2.2.1](./ss22.png)

**2.2.2**

S0 and S1 represent each of the numbers on the 2-bit select port. In our picture, we set both S0's to 0 and S1 to 1 for it to be equal to the 2-bit select port 01 in the multiplexer above.

For us to make the exact same circuit with the 4:1 multiplexer and the 3 2:1 multiplexers, first we set the select-port to 01. Then we set the S0 bits to one and the S1 bit to zero. They're mirrored in a way. 

**4.1**

**4.1.1**

If both inputs are 1 (High), the output is reset to zero. Otherwise, depending on which input we set to one the other output is going to become true (1). 

An example of this is the following picture:
[Uppgift 4.1.1](./ss411.png)

This picture has Q' to one and Q to zero. This is because the previous input from S was one, but was saved after we set it to zero. 

**4.1.2**
When both inputs are zero, the output is the previously saved one. When they're both one, the output is undefined,the outputs should complement each other but them being ones contradict this principle.
When the set input is high (1), the flip flop will be in the set state. When the reset input is high (1), the flip flop will be in the reset state.

In [our](./ss412.png) case: S = 0, R = 1, Q = 0, Q' = 1.
Q' is the inverse of Q.

**4.1.3**

The Q output is set to true (1) and stays that way however we toggle the R input.

**4.2.1**

The circuit: [4.2.1](./ss421.png)

When D is set to zero and we toggle CLK, the upper and-gate will pass a true value and therefore set Q' to true (1). The SR-latch will be in a set state. Setting the CLk to zero and toggling the D input will not have any effect since the enabler is not true and therefore none of the and-gates can pass through a value.

**4.2.2**

If the CLK is set to true, both the and-gates have one true-input. Toggling the D input will toggle which and-gate passes a true value.

**4.2.3**

It is sometimes to ones benefit to have some sort of gatekeeper for the SR latches, in our case it's the CLK input. It adds another level of security (another condition which has to be met) for the values to pass through. This can be in the form of a switch or something similar which only passes the signal if the switch is on.

One practical use of a D latch is to eliminate mechanical "bounce". Mechanical bounce implies a state of uncertainty in whether a switch, button, keypad etc. has been fully pressed or released. The trigger can bounce and send incorrect signals. The latch can be used to solve this problem by virtue of its functionality.

**4.2.4**

The problem with D latches is that you can not reliably change the state of the latch since it's not edge-triggered such as the one we're constructing in assignment 5. 

**5.1**

**5.1.1** 

[D flip-flop](./511.png)

**5.1.2**

The value of Q changes only if the CLK value is true, since the edge is high. Changing the Din value doesn't affect the output Qout if the CLK is set to false. This corresponds to a synchronous sequential logic circuit since the output is defined based on the current clock-state and of course, the input.

**5.2**

**5.2.1**

[4-bit register using D flip-flop](./521.png)

**6.1**
