# Exercise 3

# Part 1

- The roles in the group seem to work fine. The leader role is symbolic for the most part; we work better as a collective as we bounce ideas off each other. People have naturally jumped a bit back and forth between backend and frontend development

- People seem to handle problems on their own as they occur within their task. Help is offered if asked for and many of the problems are discussed in the group before the actual implementation is done.
- As a group, we have not had any problems working together. Our current method of working together seems to function.
- We communicate face-to-face at the meetings, other than that there has been some communication over slack.
- Structurally we need to become better at  setting tasks and delegating them to specific people, seems at times that the greater work load is pushed upon the people who are working on the &quot;busy&quot; areas of the project.
- We use a lot of pair programming, which means a lot of the time the same computer is used. Therefore some people&#39;s commits may seem smaller than others. This is however a great way for us to exchange knowledge between each other and we will continue doing this, but also try to even out the commits between the group members.
- Things to improve during next sprint:
  - Task delegation &amp; commit distribution
  - Deeper planned tasks. (More planning before writing the actual code)
  - Rotation of task types for better balance of knowledge. (Graphics &amp; Logics)
- A report from the group meetings can be found on the bottom of this document

# Part 2

- Customer requirements:
  - Implementations of the different game objects, and functions
  - Replace graphical placeholders
  - Optimize process heavy code.
  - Refactor code &amp; update Java-Docs
  - Splash screen
  - Sound effects and game music

- The team will prioritize tasks by use of the Workast project board.
- If changes have been made, they are done so to make it easier to implement future patches.
- We will verify that requirements have been met by testing and internal team presentation
- What has been done:
  - Able to get all types of Program cards
  - Deal 9 cards to player
  - Choose 5 cards and lock program
  - Execute chosen program
  - Visit flag
  - If the robot falls off board or into hole, it is destroyed and respawns with 6 health on last back up
  - Update backup if robot ends up on a Repair station
  - Move back up to flag.
  - Play a full round with all phases.
  - Deal new cards at beginning of new round.

# Part 3

- To run the program, pull the master branch from the Git repo. Import as a maven project and run the class &quot;main&quot; start the game.
- The tests can be found in the &quot;test&quot; folder where each test can be run by right clicking.
- The class diagram file can be opened in IntelliJ as it is a uml file



# Report from meetings

##### 28.01.2019
- Git setup
- Team building
- Sette oss inn i RoboRally

##### 05.02.2019
- Oppgavefordeling
- Klasseoppsett
- Splitting i front- og backend
- Sette oss inn i LibGDX
- Dypere forståelse av oppgaven

##### 07.02.2019
- Jobbing med grafikk
- Sette oss inn i Tiled
- Git-operasjoner
- Jobbing med logikk
- Sette oss inn i priority på kort, etc.
- Fullføring av kort
- Deck-constructur pre-prod
- Sette oss inn i player-funksjonen

##### 12.02.2019
- Lage nytt system istedenfor Tiled
- Jobbing med arkitektur
- Grafikkprototyping

##### 14.02.2019
- Sette oss inn i nytt system
- Sette oss inn i ny arkitektur
- Forbedre gammel kode

##### 19.02.2019
- Bruke nytt system og arkitektur i kode
- Lage placeholders for MVP
- Forbedre frontend
 
##### 21.02.2019
- Kommunikasjon mellom front- og backend
- Testing av bruk av kort for å bruke backend
- Planlegging for bruk av klasser

##### 26.02.2019
- Få spilleren til å flytte seg ved bruk av kort
- Fikse alle kortene til å gjøre det de skal
- Begynne på å få brettet til å aktivere
- Oppdatering av workast


##### 28.02.2019
- Få brettet til å gjøre som det skal når spilleren går på gitte ruter
- Implementere manglende ruter
- Testing av ruter
- Vise Option cards og gi riktig tekst når den trykkes på

##### 05.03.2019
- Jobbing med innlevering
- Jobbing med javadocs
- Jobbing med tester
