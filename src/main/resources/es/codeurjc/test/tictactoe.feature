Feature: TicTacToe
  As a user I want to put some moves in the board and check the result.

Scenario: Play a game and check the result.
    Given I have a tictactoe game at http://localhost:8080
    And player_one is playerOne
    And player_two is playerTwo
    When this moves are played [0,3,1,4,2]
    Then the result is playerOne wins!
