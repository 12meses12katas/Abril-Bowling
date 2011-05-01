<?php

require '../BowlingGame.php';

class GameTests extends PHPUnit_Framework_TestCase {

  function test_suma_normalmente_la_puntuacion_de_cada_tirada() {
    $this->assertEquals(20, BowlingGame::factory('11111111111111111111')->score());
    $this->assertEquals(80, BowlingGame::factory('44444444444444444444')->score());
    $this->assertEquals(30, BowlingGame::factory('12121212121212121212')->score());
  }

  function test_si_viene_un_guion_es_un_cero() {
    $this->assertEquals(10, BowlingGame::factory('----------1111111111')->score());
  }

  function test_detecta_un_strike_suma_las_dos_siguientes() {
    $this->assertEquals(30, BowlingGame::factory('X111111111111111111')->score());
    $this->assertEquals(10 + 10 + 1 + 10 + 2 + 16, BowlingGame::factory('XX1111111111111111')->score());
  }

  function test_si_hay_un_spare_suma_el_siguiente() {
    $this->assertEquals(29, BowlingGame::factory('5/111111111111111111')->score());
  }

  function test_y_suma_bien_ultimo() {
    $this->assertEquals(300, BowlingGame::factory('XXXXXXXXXXXX')->score());
    $this->assertEquals(150, BowlingGame::factory('5/5/5/5/5/5/5/5/5/5/5')->score());
  }
  
}
