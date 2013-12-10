# Enduro


- [Getting started](README.md#getting-started)
- [Simplified SCM](README.md#simplified-scm)
- [Automatic testing](README.md#automatic-testing)
- [Continuous integration](README.md#continuous-integration)

## Getting started

Navigate (in a terminal) to the folder where you'd like to put the Enduro project folder.

Then copy and paste this command:

    git clone git@github.com:buren/enduro.git && cd ~ && git clone git@github.com:buren/dot-bash.git && mv dot-bash .dot-bash && cat ~/.dot-bash/setup/importSimplifiedGit.sh >> ~/.zshrc && source ~/.zshrc

This will download the Enduro project and include a simplified SCM command line utility. 


## Simplified SCM

####Workflow:

    pvg-dev story23                 
use when starting on a new story.
This will download the latest changes from master and create a new branch named 'story23'.
You will now be ready to implement your story/task/bugfix.

    pvg-done 'Implemented Story 23'
When a story is finished, commit with a descriptive message. 
This is when you'll be ready for potential merge conflicts.
(Automatic tests will run see the Automatic testing section for details.)

    pvg-ready-to-release
When all merged conflicts (if there are any) are fixed and your ready to push to master.
(Automatic tests will run see the Automatic testing section for details.)


## Automatic testing
After ```pvg-done``` and ```pvg-ready-to-release``` a precommit hook script will be called, which will run the entire test suite and prompt if any of the tests fail. You can then either choose to abort or ignore the failing tests and commit anyway. 
    
    
## Continuous integration

After ```pvg-done``` and ```pvg-ready-to-release``` is done the project will be sent to Travis CI, which will then try to build the project and run its entire test suite.

