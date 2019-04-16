# Reflection

Add to this file to satisfy the "Reflection Updates" non-functional requirement
for this project. Please keep this document organized using Markdown. If you
click on this file in your team's GitHub repository website, then you will see
that the Markdown is transformed into nice looking HTML.

## TUES 2019-04-16 @ 11:55 PM EST

1. **DONE:** Finished the custom component that each tile in 2048 will use. Added 60FPS sliding to the tiles. Made a basic Main Menu and 2048 GUI.

2. **TODO:** Tetris Game, 2048 GUI polishing, 2048 tile merging, 2048 adding new tiles after every move, storing the high score in a file, filling out ATTRIBUTION.md file.

3. **PROB:** Had a hard time sliding the tiles around at first, but that was resolved by using a Pane instead of a Group. Also, when moving the tiles, 2 tiles of the same value will merge prematurely, and when the merging tile is removed from the scene, it throws a ConcurrentModifierException, which we do not know yet how to fix.