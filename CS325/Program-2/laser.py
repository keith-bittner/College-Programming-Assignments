################################################################################
#
#	laser.py
#
#	Keith Bittner
#	CS328 - Winter 2020
#
#
#
################################################################################
import pygame
from pygame.sprite import Sprite

##########
#
#
#
##########
class Laser(Sprite):

	speed = 5.0
	white = (255, 255, 255)

##########
#
#
#
##########
	def __init__(self,sa_game):

		super().__init__()

		self.screen = sa_game.screen

		#our laser blasts aren't going to be based on an image
		#so we build a rect object for them
		self.rect = pygame.Rect(0,0,3,15)

		#set the laser blast to be initally located at the top of the ship
		self.rect.midtop = sa_game.newPlayer.rect.midtop

		self.y = float(self.rect.y)

##########
#
#
#
##########
	def update(self):

		self.y -= self.speed
		self.rect.y = self.y

##########
#
#
#
##########
	def draw_laser(self):

		pygame.draw.rect(self.screen, self.white, self.rect)
