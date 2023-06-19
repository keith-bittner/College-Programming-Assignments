################################################################################
#
#	player.py
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
class Player(Sprite):

	move_Left = False
	move_Right = False

##########
#
#
#
##########
	def __init__(self, sa_game):

		super().__init__()
		self.speed = 5
		self.screen = sa_game.screen
		self.screen_rect = sa_game.screen.get_rect()
		selectedSize = sa_game.newScreen
		self.width = selectedSize[0]

		self.image = pygame.image.load("graphics/player.png")
		self.image = pygame.transform.scale(self.image, (50, 50))

		self.rect = self.image.get_rect()
		self.rect.midbottom = self.screen_rect.midbottom

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
	def is_killed(self):

		self.rect.y = -9999

##########
#
#
#
##########
	def update(self):

		if (self.move_Left == True):
			if (self.rect.x > 0):
				self.rect.x -= self.speed

		if (self.move_Right == True):
			if (self.rect.x < (self.width - 50)):
				self.rect.x += self.speed
