# Enduro [![Build Status](https://travis-ci.org/buren/enduro.png?branch=master)](https://travis-ci.org/buren/enduro)

- [Getting started](#getting-started)
- [Simplified VCS](#simplified-vcs)
- [Automatic testing](#automatic-testing)
- [Continuous integration](#continuous-integration)

## Getting started

If you haven't generated ssh-keys, then you need to do so.
You can follow the guide here: https://help.github.com/articles/generating-ssh-keys

Then copy and paste this command:

    curl https://raw2.github.com/buren/enduro/master/install_enduro.sh | bash
install logs are saved to ~/eda260_pvg/\_*_install.log.

This will download the Enduro project and include a simplified VCS command line utility. 


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
(Automatic test will run see the Automatic testing section for details.)


## Automatic testing
After ```gs done``` a precommit hook script will be called, which will run the entire test suite and prompt you to answer if any of the tests fail. You can then either choose to abort or ignore failing tests and commit anyway.
    
    
## Continuous integration

After ```gs done``` is done the project will be sent to Travis CI, which will then build the project and run its entire test suite. [Travis CI for Enduro.](https://travis-ci.org/buren/enduro)
