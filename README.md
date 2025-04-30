# Asteroids Remake
Created by Kian Naeimi, Sean Sweeney, and Sam Kennedy

## System requirements
    This game was designed with a standard 1920 by 1080 pixel screen in mind, but feel free to adjust the canvas dimensions to your liking. It's also not graphically intensive by any metric, so if you meet these minimum specifications:
        Java: 17/21 installed
        OS: Windows 7 (64-bit) or newer, macOS 10.10 or newer, any mainstream Linux distro
        CPU: Dual core @ 1.6 GHz or newer
        Graphics: Integrated graphics or better
        RAM: ~1 GB for the JVM 
        Storage: 50-100 MB free
    You should be golden. 

## Running the program
    To launch the game, navigate to AsteroidsGame.java and run the main method by hitting the 'Run Java' button in the top right corner of the
    IDE. Your movement is handled by the up, left, and right arrow. Press up to go in the direction the tip of the rocket is pointing towards,
    and press left/right to change the angle of the forward vector. You will immediately be ambushed by a gang of asteroids... but don't worry-
    you have two types to hold them off:
    
        Bullets: Line up the tip of the ship with where you want to fire your bullet(s), and then press space to launch a quick bullet or two. 
        
        Beams: Similarly line up your shot, but this time press the F key to fire a beam that spans the entirety of the canvas, taking out any 
        asteroids in its way. But be careful- using it stops your movement briefly, so you'll have to time your shots out. Hint: If you see a grouping of asteroids, use a beam to quickly dispatch them.

    Meteors: Each meteor has effectively three lives: The first is taken when you shoot it in it's first form (which splits the meteor into two), and the other two lives are taken when you shoot resulting split meteors. Like the ship, meteors will wrap from one side to the other,
    so be careful when using the bounds to escape gangs of asteroids- there might be another on the other side spawning in. 
    
    Lives: You have 3 lives, represented by the green ship objects on the top left corner of the screen. Each time you lose one, 
    a ship will be removed to reflect your failure. Once you lose all 3 available lives, the game ends, and the window will close. Restart
    by running the main method in AsteroidsGame again.

    Score: Every time you hit a meteor (of any size), your score will go up by 20. Try to see how high you can get!

## Acknowledgements
    This program was inspired by two versions of Asteroids: First, the original Atari game (1979, Lyle Rains/Ed Logg). And secondly,
    the Adamvision Studios/SneakyBox remake: Asteroids Recharged (2021, Ieva Auželytė).
    
    Outside rescources we used: Youtube for movement and key ideas/logic, StackOverflow for bullet/meteor delay ideas.

    Preceptors that helped us: Lewis with initial bullet projectile logic/debugging, Marvin with the hundreds of ConcurrentModificationExceptions we ran into (along with debugging other types of crashes).
    
    Help from Professor Bret Jackson with linear algebra concepts for beam collision. 
    
    This project would be nowhere near where it is now without these people (In real life or in decade old StackOverflow threads/YouTube videos). All thanks goes to them for tireless help with debugging, ideas, and tolerating countless rubber-duck style debugging conversations. 

## Known issues
    Are there fundamental design limitations that users should be aware of?
    Will add if found before due.
    Are there specific bugs, glitches, or shortcomings that users should be aware of?
    Will add if found before due.

## Societal impact
    Our game is currently inaccessible to visually-impaired or blind players.
    This is because it does not use sound effects and is designed around the player's ability to see what is happening onscreen.
    Our game also requires keyboard input, so those unable to press keys will require an accessibility aid to play.

