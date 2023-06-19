################################################################################
#
#	space_aliens.py
#
#	Keith Bittner
#	CS328 - Winter 2020
#
#
#
################################################################################
import sys, time
import pygame as py
from player import Player
from enemy import Enemy
from laser import Laser

#initialize pygame
py.init()

class Space_Aliens:

	#globals
	font = "graphics/ARCADE_N.TTF"
	red = (255, 0, 0)
	black = (0, 0, 0)
	white = (255, 255, 255)

	def __init__(self):

		#make the game screen, set window name, and set its bg image
		defaultSize = (800, 400)
		self.newScreen = defaultSize
		self.screenWidth = 800
		self.screen = py.display.set_mode(defaultSize)
		self.screen_rect = self.screen.get_rect()
		py.display.set_caption("Space Aliens: CS 328 Assignment 2")
		self.bg_image = py.image.load("graphics/image1.png")

		#load up the sound files
		self.laser_sound = py.mixer.Sound("audio/laser.ogg")
		self.player_killed = py.mixer.Sound("audio/player_killed.ogg")
		self.enemy_killed = py.mixer.Sound("audio/enemy_killed.ogg")
		self.bg_music = py.mixer.music.load("audio/game_music.ogg")
		self.bg_music = py.mixer.music.set_volume(0.4)

		#initialize sprite groups
		self.player = py.sprite.Group()
		self.enemies = py.sprite.Group()
		self.lasers = py.sprite.Group()

		#counters
		self.killCount = 0
		self.enemyCount = 0
		self.playerKilled = False

		#end game screens
		self.gameover1 = self.format_text("Game Over", self.font, 50, self.white)
		self.gameover2 = self.format_text("You Lose!", self.font, 50, self.white)
		self.youwin = self.format_text("You Win!", self.font, 50, self.white)
		self.youwinscore = self.format_text("Final Score:", self.font, 25, self.white)
		self.gameover1_rect = self.gameover1.get_rect()
		self.gameover2_rect = self.gameover2.get_rect()
		self.youwin_rect = self.youwin.get_rect()
		self.youwinscore_rect = self.youwinscore.get_rect()

##########
#
#	sound effects
#
##########
	def sound_effects(self, soundFile):

		soundFile.play()

##########
#
#	spawn player controlled ship
#
##########
	def spawn_player(self):

		self.newPlayer = Player(self)
		self.player.add(self.newPlayer)

##########
#
#	spawn the enemies
#
##########
	def spawn_enemy(self):

		for i in range (5):
			self.newEnemy = Enemy(self)
			self.newEnemy.rect.x = i * 60
			self.enemies.add(self.newEnemy)

##########
#
#	fire the lasers
#
##########
	def fire_lasers(self):

		self.myLaser = Laser(self)
		self.lasers.add(self.myLaser)

##########
#
#	build the laser group
#
##########
	def update_lasers(self):

		for shoot in self.lasers.sprites():
			shoot.draw_laser()
			shoot.update()
		py.display.update()

##########
#
#	format text for screen blitting
#
##########
	def format_text(self, message, textFont, textSize, textColor):

		newFont = py.font.Font(textFont, textSize)
		newText = newFont.render(message, 0, textColor)

		return newText

##########
#
#	main game menu
#
##########
	def main_menu(self):

		py.mixer.music.play(loops=-1)
		game_Menu = True
		selection = "default"

		#main menu event loop
		while game_Menu:
			for event in py.event.get():
				if event.type == py.QUIT:
					sys.exit()
				if event.type == py.KEYDOWN:
					if event.key == py.K_ESCAPE:
						sys.exit()
					if event.key == py.K_o:
						self.option_menu()
					if event.key == py.K_UP:
						selection = "start"
					elif event.key == py.K_DOWN:
						selection = "quit"
					if event.key == py.K_RETURN:
						if selection == "start":
							self.start()
						if selection == "quit":
							sys.exit()

			self.screen.fill(self.black)

			#set main menu text
			title = self.format_text("Space Aliens", self.font, 50, self.white)
			title2 = self.format_text("CS328 - Assignment 2", self.font, 15, self.white)
			title3 = self.format_text("Keith Bittner", self.font, 15, self.white)

			if selection == "start":
				text_Start = self.format_text("START", self.font, 25, self.red)
			else:
				text_Start = self.format_text("START", self.font, 25, self.white)
			if selection == "quit":
				text_Quit = self.format_text("QUIT", self.font, 25, self.red)
			else:
				text_Quit = self.format_text("QUIT", self.font, 25, self.white)

			options = self.format_text("Press 'O' for options.", self.font, 15, self.white)

			#main menu rects
			title_rect = title.get_rect()
			title2_rect = title2.get_rect()
			title3_rect = title3.get_rect()
			start_rect = text_Start.get_rect()
			quit_rect = text_Quit.get_rect()
			options_rect = options.get_rect()

			#blit main menu
			self.screen.blit(title, (self.screenWidth / 2 - (title_rect[2] / 2), 80))
			self.screen.blit(title2, (self.screenWidth / 2 - (title2_rect[2] / 2), 180))
			self.screen.blit(title3, (self.screenWidth / 2 - (title3_rect[2] / 2), 200))
			self.screen.blit(text_Start, (self.screenWidth / 2 - (start_rect[2] / 2), 280))
			self.screen.blit(text_Quit, (self.screenWidth / 2 - (quit_rect[2] / 2), 340))
			self.screen.blit(options, (self.screenWidth / 2 - (options_rect[2] / 2), 380))

			py.display.update()

##########
#
#	option menu for screen resolution
#
##########
	def option_menu(self):

		option_Menu = True
		selection = "default"
		screenSize1 = (1200, 800)
		screenSize2 = (1000, 600)

		#option menu event loop
		while option_Menu:
			for event in py.event.get():
				if event.type == py.QUIT:
					sys.exit()
				if event.type == py.KEYDOWN:
					if event.key == py.K_ESCAPE:
						sys.exit()
					if event.key == py.K_UP:
						selection = "option1"
					elif event.key == py.K_DOWN:
						selection = "option2"
					if event.key == py.K_RETURN:
						if selection == "option1":
							self.newScreen = screenSize1
							return
						if selection == "option2":
							self.newScreen = screenSize2
							return

			self.screen.fill(self.black)

			#set option menu text
			title = self.format_text("Option Menu", self.font, 50, self.white)
			title2 = self.format_text("Select your preferred screen size.", self.font, 15, self.white)

			if selection == "option1":
				text_Option1 = self.format_text("1200 x 800", self.font, 25, self.red)
			else:
				text_Option1 = self.format_text("1200 x 800", self.font, 25, self.white)
			if selection == "option2":
				text_Option2 = self.format_text("1000 x 600", self.font, 25, self.red)
			else:
				text_Option2 = self.format_text("1000 x 600", self.font, 25, self.white)

			#option menu rects
			title_rect = title.get_rect()
			title2_rect = title2.get_rect()
			option1_rect = text_Option1.get_rect()
			option2_rect = text_Option2.get_rect()

			#blit option screen
			self.screen.blit(title, (self.screenWidth / 2 - (title_rect[2] / 2), 80))
			self.screen.blit(title2, (self.screenWidth / 2 - (title2_rect[2] / 2), 200))
			self.screen.blit(text_Option1, (self.screenWidth / 2 - (option1_rect[2] / 2), 250))
			self.screen.blit(text_Option2, (self.screenWidth / 2 - (option2_rect[2] / 2), 310))

			py.display.update()

##########
#
#	main game function
#
##########
	def start(self):

		#load new screen size and change to bg image
		self.screen = py.display.set_mode(self.newScreen)
		self.bg_image = py.transform.scale(self.bg_image, self.newScreen)
		self.bg_color = (self.black)
		self.screen.blit(self.bg_image, (0, 0))

		#load clock for frames per second use
		clock = py.time.Clock()

		#load the scoreboard
		scoreBoard1 = self.format_text("Score:", self.font, 25, self.white)
		scoreBoard2 = self.format_text(str(self.killCount), self.font, 25, self.white)
		scoreBoard1_rect = scoreBoard1.get_rect()
		scoreBoard2_rect = scoreBoard2.get_rect()

		#spawn player and enemies
		self.spawn_player()
		self.spawn_enemy()

		#start the game event loop
		while 1: #infinite

			#set frames per second
			clock.tick(60)

			#go through each event trying to exit the game
			for event in py.event.get():        
				if event.type == py.QUIT: 
					sys.exit()
				if event.type == py.KEYDOWN:
					if event.key == py.K_ESCAPE:
						sys.exit()
					if event.key == py.K_LEFT:
						self.newPlayer.move_Left = True
					if event.key == py.K_RIGHT:
						self.newPlayer.move_Right = True
					if event.key == py.K_SPACE:
						self.fire_lasers()
						self.sound_effects(self.laser_sound)
				elif event.type == py.KEYUP:
					if event.key == py.K_LEFT:
						self.newPlayer.move_Left = False
					if event.key == py.K_RIGHT:
						self.newPlayer.move_Right = False

			#erase screen and refill with bg image and scorboard
			self.screen.blit(self.bg_image, (0, 0))
			self.screen.blit(scoreBoard1, ((self.newScreen[0] - 175), 0))
			self.screen.blit(scoreBoard2, ((self.newScreen[0] - 25), 0))

			#enemy crashes into player
			for i in  py.sprite.groupcollide(self.player, self.enemies, True, True):
				self.newPlayer.is_killed()
				i.is_killed()
				self.sound_effects(self.player_killed)
				self.playerKilled = True

			#laser hits enemy
			for i in py.sprite.groupcollide(self.enemies, self.lasers, True, True):
				self.killCount += 1
				self.enemies.remove(i)
				self.sound_effects(self.enemy_killed)
				scoreBoard2 = self.format_text(str(self.killCount), self.font, 25, self.white)

			#did you win?				
			if self.killCount == 5:
				self.screen.fill(self.black)
				self.screen.blit(self.youwin, (self.screenWidth / 2 - (self.youwin_rect[2] / 2), 100))
				self.screen.blit(self.youwinscore, (self.screenWidth / 2 - (self.youwinscore_rect[2] / 2), 150))
				self.screen.blit(scoreBoard2, (self.screenWidth / 2 - (scoreBoard2_rect[2] / 2), 175))

			#gameover
			if self.playerKilled == True:
				self.screen.fill(self.black)
				self.screen.blit(self.gameover1, (self.screenWidth / 2 - (self.gameover1_rect[2] / 2), 100))
				self.screen.blit(self.gameover2, (self.screenWidth / 2 - (self.gameover2_rect[2] / 2), 150))

			#update and display everything
			self.update_lasers()
			for i in self.enemies:
				i.blitme()
			self.newPlayer.update()
			for j in self.enemies:
				j.update()
			for t in self.enemies:
				if t.rect.left < 0 or t.rect.right > self.newScreen[0]:
					for s in self.enemies:
						s.bounce()
			self.newPlayer.blitme()
			py.display.update()

#start the game
if __name__ == '__main__':
	myGame = Space_Aliens()
	myGame.main_menu() 
