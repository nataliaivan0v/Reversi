We implemented until level 2 for Assignment 9. 
For level 0, we changed the HexPanel class to implement hints. We utilized the decorator pattern by
creating an interface Painter. There was no editing of the rendering code.
For level 1, we implemented the SquareReversi class by implementing the same interface Reversi that was used 
for our original model implementation. Additionally, we implemented SquareReversiTextualView that prints out 
a textual representation of the board and implements the ReversiTextual view interface used also by our original 
textual view. All the classes coexist and don’t interfere with one another.
For level 2, we created the extra classes ReversiSquareGraphics (implements the same interface as the hexagonal view)
and SquarePanel.