################################################################################
#
#	enemy.py
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
class Enemy(Sprite):

##########
#
#
#
##########
	def __init__(self, sa_game):

		super().__init__()
		self.speed = [10, 0]
		self.access = 0
		self.screen = sa_game.screen
		self.screen_rect = sa_game.screen.get_rect()
		selectedSize = sa_game.newScreen
		self.width = selectedSize[0]

		self.image = pygame.image.load("graphics/enemy.png")
		self.image = pygame.transform.scale(self.image, (30, 30))

		self.rect = self.image.get_rect()
		self.rect.y = 30

##########
#
#
#
##########
	def blitme(self):

		self.screen.blit(self.image, self.rect)

##########
#
#
#
##########
	def bounce(self):

		self.speed[0] = -self.speed[0]
		self.rect.y += 30
##########
#
#
#
##########
	def update(self):

		self.rect = self.rect.move(self.speed)	
		
