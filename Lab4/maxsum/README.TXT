//NOT DONE YET....


------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: maxsum
PURPOSE OF PROJECT: Examine and construct a few basic algorithms 	
VERSION or DATE: 150503
HOW TO START THIS PROJECT: 
AUTHORS: Anton Eksell	
USER INSTRUCTIONS:

Exercise 1:
Timings: 

 N = 10 all have a time of 0 microsec wich suggests small enough batches of data isn't very interesting to optimize since the calculations arn't big enough to make a significant difference.

Algorithm #3	N = 10, time = 0 microsec
Algorithm #4	N = 10, time = 0 microsec
Algorithm #2	N = 10, time = 0 microsec
Algorithm #1	N = 10, time = 0 microsec

N = 100 are rather uninteresting to compare since the clock of the computer isn't precise enough. You can still see that there's a significant difference between the Algorithms.
Algorithm #3	N = 100, time = 1 microsec
Algorithm #4	N = 100, time = 3 microsec
Algorithm #2	N = 100, time = 6 microsec
Algorithm #1	N = 100, time = 91 microsec

N = 1000 the computations starts to get large enough for the timer to give a decent hint about the algorithms Big-Oh, from here on you're able to discern a pattern. 
Algorithm #3	N = 1000, time = 17 microsec
Algorithm #4	N = 1000, time = 39 microsec
Algorithm #2	N = 1000, time = 352 microsec
Algorithm #1	N = 1000, time = 50848 microsec

A#3 is here about 10 times higher (169/17), than the previous time which correspond to the increase of N (10^3->10^4). This truly seem to be the O(N) algorithm.
A#4 is here just above 10 times higher (417/39 = 10.69), probably still within margin of error for  O(N).
A#2 grow about 10^2 times and seem to be O(N^2) with the 10 fold increase
A#1 grow about 10^3 times and seem to be O(N^3) with the 10 fold increase.
 
Algorithm #3	N = 10000, time = 169 microsec 		
Algorithm #4	N = 10000, time = 417 microsec
Algorithm #2	N = 10000, time = 28517 microsec
Algorithm #1	N = 10000, time = 59833000 microsec

A4# is now starting to slowly outgrow A#3, (4608/417 = 11,05 ) and therefore keep showing signs to be O(N*log(N))
A#2 stay around 100 times bigger
A#1 has turned to large to wait for and the program won't measure it.  

Algorithm #3	N = 100000, time = 1714 microsec
Algorithm #4	N = 100000, time = 4608 microsec
Algorithm #2	N = 100000, time = 2651000 microsec


Algorithm #3	N = 1000000, time = 17707 microsec
Algorithm #4	N = 1000000, time = 50075 microsec