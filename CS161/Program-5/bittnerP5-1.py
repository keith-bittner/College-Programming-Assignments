##################################################
#
#	bittnerP5.py
#
#	This program simulates an electronic polling station.  It uses logical
#	functions in combination with lists, 'while' loops, 'for' loops, 'if',
#	and 'else' statements.  Each logical function never returns a set variable
#	due to the extensive usage of lists.  While the lists are not global, each
#	function makes changes to the specific lists they call upon.
#
#	Programmer: Keith Bittner
#	Written: December 2, 2018
#
##################################################
#
#	main
#
#	This is the main program function.  All lists are initialized in this
#	function and then passed around through the different logical functions.
#
##################################################
def main():

	candidateTotal = int(input("Please enter the number of candidates: "))
	choiceList = [0] * candidateTotal
	candidateList = [0] * candidateTotal
	partyList = [0] * candidateTotal
	voteCountList = [0] * candidateTotal
	votePercentList = [0] * candidateTotal
	historyGraph = [0] * candidateTotal
	
	workerSetup(choiceList, candidateList, partyList)
	
	votingFunc(choiceList, candidateList, partyList, voteCountList)

	votingPercent(voteCountList, votePercentList)

	resultReport(candidateList, partyList, voteCountList, votePercentList)

	historyReport(candidateList, votePercentList, historyGraph)
##################################################
#
#	workerSetup
#
#	This function asks the election worker to input the name and party for each
#	candidate according to the number of candidates entered.
#
##################################################
def workerSetup(choiceList, candidateList, partyList):

	if len(candidateList) > 0:
		for i in range(len(candidateList)):
			candidateName = input("Please enter the name of candidate " \
				+ str(i + 1) + ": ")
			partyName = input("Please enter " + candidateName + "'s party: ")
			choiceList[i] = i + 1
			candidateList[i] = candidateName
			partyList[i] = partyName

	return
##################################################
#
#	votingFunc
#
#	Asks for the voter to cast a vote for their candidate choice.  Displays the
#	voterDisplay function.  Will close the poll of an election worker enters
#	'-1' into the voter prompt.
#
##################################################
def votingFunc(choiceList, candidateList, partyList, voteCountList):

	voteHeader = "\nVote by entering the number of your chosen candidate.\n"
	print(voteHeader)
	voterDisplay(choiceList, candidateList, partyList)
	voterEntry = int(input("\nEnter your vote: "))

	while voterEntry >= 0:
		if voterEntry > 0 and voterEntry <= len(candidateList):
			voteCountList[voterEntry - 1] = voteCountList[voterEntry - 1] + 1
			print(voteHeader)
			voterDisplay(choiceList, candidateList, partyList)
			voterEntry = int(input("\nEnter your vote: "))
		else:
			print(voteHeader)
			voterDisplay(choiceList, candidateList, partyList)
			voterEntry = int(input("\nEnter your vote: "))

	return
##################################################
#
#	voterDisplay
#
#	This function sends votingFunc the candidate's vote number, name, and party
#	for the voter to see.
#
##################################################
def voterDisplay(choiceList, candidateList, partyList):

	for i in range(len(candidateList)):
		print("%-5s %-12s %10s" % (str(choiceList[i]), candidateList[i], "(" \
			+ partyList[i] + ")"))
##################################################
#
#	votingPercent
#
#	This function calculates the percentage of total votes that a candidate
#	received.
#
##################################################
def votingPercent(voteCountList, votePercentList):

	votingTotal = sum(voteCountList)

	for i in range(len(voteCountList)):
		votePercentList[i] = round((voteCountList[i] / votingTotal) * 100, 2)

	return
##################################################
#
#	resultReport
#
#	This function prints the final election results after the election worker
#	enters '-1' into the voter prompt.
#
##################################################
def resultReport(candidateList, partyList, voteCountList, votePercentList):

	resultHead1 = "\n=============== Election Results ===============\n\n"
	resultHead2 = "  Candidate\tParty\tVotes\tPercent\n"

	print(resultHead1, resultHead2)

	for i in range(len(candidateList)):
		print("%-12s %7s %7s %10.2f" % (candidateList[i], partyList[i], \
			str(voteCountList[i]), float(votePercentList[i])))
##################################################
#
#	historyReport
#
#	This function prints a histogram bar graph to display a candidates percent
#	of votes received. "*" indicates 1% of votes while "+" indicates < 1% of
#	votes.
#
##################################################
def historyReport(candidateList, votePercentList, historyGraph):

	wholePercent = "*"
	halfPercent = "+"

	print("\n======= Histogram =======\n")

	for i in range(len(candidateList)):
		if round(votePercentList[i]) > votePercentList[i]:
			historyGraph[i] = (round(votePercentList[i] - 1) * wholePercent) \
				+ halfPercent
			print("%-12s %5s" % (candidateList[i], "|"), historyGraph[i])
		else:
			historyGraph[i] = round(votePercentList[i]) * wholePercent
			print("%-12s %5s" % (candidateList[i], "|"), historyGraph[i])

main()
