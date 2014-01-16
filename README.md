# Enduro [![Build Status](https://travis-ci.org/buren/enduro.png?branch=master)](https://travis-ci.org/buren/enduro)

- [Getting started](README.md#getting-started)
- [Simplified SCM](README.md#simplified-scm)
- [Automatic testing](README.md#automatic-testing)
- [Continuous integration](README.md#continuous-integration)

## Getting started

Navigate (in a terminal) to the folder where you'd like to put the Enduro project folder.

Then copy and paste this command:

    git clone git@github.com:buren/enduro.git && cd ~ && git clone git@github.com:buren/enduro.git && cd ~ &&  curl https://raw.github.com/buren/dot-bash/master/setup.sh | bash

This will download the Enduro project and include a simplified SCM command line utility. 


## Simplified VCS

VCS tool: [git-story](https://github.com/buren/git-story)

####Workflow:

    $ gs dev story23                 
use when starting on a new story.
This will download the latest changes from master and create a new branch named 'story23'.
You will now be ready to implement your story/task/bugfix.

    $ gs done 'Implemented Story 23'
When a story is finished, commit with a descriptive message. 
This is when you'll be ready for potential merge conflicts.
(Automatic tests will run see the Automatic testing section for details.)


## Automatic testing
After ```gs done``` a precommit hook script will be called, which will run the entire test suite and prompt if any of the tests fail. You can then either choose to abort or ignore the failing tests and commit anyway. 
    
    
## Continuous integration

After ```gs done``` is done the project will be sent to Travis CI, which will then build the project and run its entire test suite.

 
