# Enduro

## Getting started

Navigate (in a terminal) to the folder where you'd like to put the Enduro project folder. Then copy and paste this command:

    git clone git@github.com:buren/enduro.git && cd ~ && git clone git@github.com:buren/dot-bash.git && mv dot-bash .dot-bash && cat ~/.dot-bash/setup/importSimplifiedGit.sh >> ~/.zshrc && source ~/.zshrc

This will download the Enduro project and include a simplified SCM command line utility. 


## Simplified SCM

Workflow:

    pvg-dev story23                 
use when starting on a new story.
This will download the latest changes from master and create a new branch named 'story23'.
You will now be ready to implement your story/task/bugfix.



    pvg-done 'Implemented Story 23'
When a story is finished, commit with a descriptive message. 
This is when you'll be ready for potential merge conflicts.

    pvg-ready-to-release
When all merged conflicts (if there are any) are fixed and your ready to push to master.
