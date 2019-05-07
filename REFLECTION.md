# Reflection

Add to this file to satisfy the "Reflection Updates" non-functional requirement
for this project. Please keep this document organized using Markdown. If you
click on this file in your team's GitHub repository website, then you will see
that the Markdown is transformed into nice looking HTML.

## TUES 2019-04-16 @ 11:55 PM EST

1. **DONE:** Finished the custom component that each tile in 2048 will use. Added 60FPS sliding to the tiles. Made a basic Main Menu and 2048 GUI.

2. **TODO:** Tetris Game, 2048 GUI polishing, 2048 tile merging, 2048 adding new tiles after every move, storing the high score in a file, filling out ATTRIBUTION.md file.

3. **PROB:** Had a hard time sliding the tiles around at first, but that was resolved by using a Pane instead of a Group. Also, when moving the tiles, 2 tiles of the same value will merge prematurely, and when the merging tile is removed from the scene, it throws a ConcurrentModifierException, which we do not know yet how to fix.


## TUES 2019-04-23 @ 11:55 PM EST

1. **DONE:** Finished movement methods and collision detection for 2048 game.

2. **TODO:** Tetris Game, 2048 tile merging, storing high score, filling out ATTRIBUTION.md file.

3. **PROB:** We had a problem where we could move tiles while a previous move was still in process, we fixed it by using 4 different timelines for each direction instead of the suggested 1.

    
## TUES 2019-04-30 @ 11:55 PM EST

1. **DONE:** Actually finished movement, merging, and collisions for 2048 games (no bugs). Started making skeleton code for Tetris shapes.

2. **TODO:** Tetris Game, 2048 score keeping, 2048 new game button.

3. **PROB:** When buttons are on the scene, using the arrow keys can move focus to the buttons, making moving the tiles on the game board impossible, as it does not recognize that focus belongs to the Pane holding the tiles.


## TUES 2019-05-07 @ 01:00 PM EST

1. **DONE:** Finished everything: 2048, Tetris, javadoc, etc.

2. **TODO:** N/A

3. **PROB:** We had a problem where the clearLines method would not correctly pull down rows after clearing a row, but we managed to figure it out. Apparently GridPane doesn't like it when a null object is assigned to a grid position.