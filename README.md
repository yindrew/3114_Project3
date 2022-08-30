# ExternalSort - An external sorting program.

We start our project off with a few limitations. Our input buffer and output buffer can only be 8196 bytes. Our memory storage can store 65536 byes. We are then given a large file filled with numbers that we would have to sort from smallest to largest. We replace the file with the newly sorted file.

### Step one - Replacement Selection

We first break up the file into multiple groups of sorted data. We will call each group of data a "run". By using our inputBuffer to allocate memory we can use less calls to our actual storage. We use our input buffer to continously fill our min heap which is a data structure which holds the smallest values in the root node and the largest values in the leaf nodes. Once our min heap is full, we take the smallest value from it and start a run. we than continiously fill the min heap and take the smallest value out and put it into our run. We will continue to do this until the file is finished processing.

### Step two - Multiway Merge

We use 8 different buffers that each point to the beginning of the first 8 runs. We compare the first values in each of the 8 buffers and take out the smallest and put it into a new larger run that is comprised of the 8 runs. This will sort 8 runs at a time, and once we finish a whole pass through the file, we repeat the process. 

### finished

After our last multiway merge pass - which should have 8 or less runs in the file, the file should be completely sorted. 

This project implemented Min Heaps and buffers. It used algorithms such as multiway merge and replacement selection.
