# ExternalSort - An external sorting program

We start our project off facing resource constraints. Our input buffer and output buffer can only be 8196 bytes. Our memory storage can store 65536 bytes. We are then given a large file filled with numbers that we would have to sort from smallest to largest. We replace the original unsorted file with the newly sorted file.

### Step One - Replacement Selection

We first break up the file into multiple groups of sorted data using Replacement Selection. Replacement Selection is an algorithim that uses a minHeap which holds the smallest value in the root node and the largest values in the leaf nodes. We will then populate each group of sorted data by removing the smallest value continuously from the minHeap and adding new values into the heap from the file using a input buffer. 

### Step Two - Multiway Merge

We use 8 different buffers that each point to the beginning of the first 8 groups of sorted data. We compare the first values in each of the 8 buffers and take out the smallest and put it into a new larger run that is comprised of the 8 runs. This will sort 8 runs at a time, and once we finish a whole pass through the file, we repeat the process until there is only one run left.
