Feature: TicTacToe
  As a user I want to put some moves in the board and check the result.

Scenario: Play a game and check the result.
    Given I have a tictactoe game
    And player_one is 'player one'
    And player_two is 'player two'
    When this moves are played [0,3,1,4,2]
    Then the result is player_one wins
