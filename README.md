##Traversal Tree Forest
Algorithm able to load a forest of trees and traverse them.

* Traversal Tree Forest
Able to build, in a recursive way, a non-binary forest of trees where each node has a link to the left most child and
a right link to the right most sibling.

A new node could be
** a child or a sibling of the current node so it is added at the first call
** a child or a sibling of another node so it is added at the next first recursive calls.

First part is checking if the new node has to be a child, the second part if it has to be a sibling.
__Pre-condition:__ the parent _must exists_.

The exit condition from the recursion is the call to the _new_ operator and the return of the new node.

Many tests implement some use cases to provide the working condition of the data structure and how it is traversed to
 collect or visit all the existing nodes.
