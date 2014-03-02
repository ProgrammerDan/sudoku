simple-solver
=============

A code-golf submission for sudoku: [create a sudoku solution checker](http://codegolf.stackexchange.com/questions/22443/create-a-sudoku-solution-checker).

Basically, trying to make the smallest solver possible. I definitely didn't win, but my solution is 260 characters by the rules of the challenge. Not bad, for Java!

**Input**

To achieve this I assume that the sudoku puzzle will be passed in as a java multidimensional array, like so:

    s(new int[][] {
        {1,2,3,4,5,6,7,8,9},
        {4,5,6,7,8,9,1,2,3},
        {7,8,9,1,2,3,4,5,6}, 
        {2,3,1,5,6,4,8,9,7},
        {5,6,4,8,9,7,2,3,1},
        {8,9,7,2,3,1,5,6,4},
        {3,1,2,6,4,5,9,7,8},
        {6,4,5,9,7,8,3,1,2},
        {9,7,8,3,1,2,6,4,5}});

**Output**
 
The solver returns "0" if valid solution, "1" if not.

**How it works**

I basically just create my own number base with sufficient resolution in each digit that I only have to do three numeric comparisons after passing through the puzzle once to know if it's valid. I chose base 49 for this problem, but any base larger than 45 would be sufficient.

A (hopefully) clear example: imagine that every "row" in the sudoku puzzle is a single digit in a base-49 number. We'll represent each digit in the base-49 number as a base-10 number in a vector for simplicity. So, if all rows are "correct" we expect the following base-49 number (as a base-10 vector):

    (45,45,45,45,45,45,45,45,45)

or converted to a single base-10 number: `1526637748041045`

Follow similar logic for all the columns, and same for the "sub-grids". Any value encountered in the final analysis that doesn't equal this "ideal number" means the puzzle solution is invalid.

Edit to solve all-5s vulnerability and other related issues: I add a fourth base-49 number, based on the idea that there should be 9 of each number in every puzzle. So, I add 5 to each digit in the base-49 number for each occurrence of the base-10 number that represents the digit's index. An example, if there are 10 9's and 9 8's, 9 7's, 8 6's, and 9 of all others, you'd get a base-49 number (as a base-10 vector of size 10 to deal with overflow):

    (1, 1, 45, 45, 40, 45, 45, 45, 45, 45)

Which will fail when compared against our "ideal" base-49 number.

My solution takes advantage of this mathematical solution, to avoid as much as possible looping and comparison. I simply use a long value to store each base-49 number as a base-10 number and use a lookup array to get the "factors" for each base-49 digit during column/row/subgrid check value computation.

**Final notes**

There are other possible approaches I've seen folks use involving bitmasks and such; I haven't verified they work, but such a refactor might allow a smaller program size.

Note, `SS_min.java` has a number of different structures and min-ified solutions in it. It is compilable and runnable. `true_min.java` is not compilable, but contains the "final" solution for the code-golf problem. It's a function, so wrap it in a class first before compiling and executing.
