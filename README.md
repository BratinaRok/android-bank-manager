# project-android-template

This project is a template for any hyperskill android project you want to create.

## Getting Started

- Clone template 
  - On AndroidStudio
    File -> New -> Project From Version Control ->
    Url: https://github.com/hyperskill/android-projects-template.git

- Change remote repository
  - Git -> Manage Remotes -> click origin -> remove
  - Git -> Manage Remotes -> add -> Url: <make-a-new-github-repository-and-link-it-here.git>

- Rename folders and related variables
  - Change Course tab to Project tab -> right click 'Project Name' folder  -> refactor -> rename

  - Also with right click methods rename test/java/org/hyperskill/projectname and main/.../projectname obs: all lowercase and without separators

  - On AndroidManifest.xml change _package_, make sure _activity name_ has already changed

  - On course-info.yaml change _title_ and make sure _content_ has already changed

  - On module build.gradle, which is the one under stage1 folder, change _applicationId_

  - On string.xml change _app_name_

- Commit initial commit for project
  - On Commit tab add every file, write message "Project Name initial commit" -> commit and Push

- Next
  - Read
      - [Defining projects](documents/outdated/DefiningProject.md)
      - [Defining stages](documents/outdated/DefiningStages.md)

## Important

Your project will be reviewed in many different stages of the project creation. The author should
be available for receiving e-mail messages and also for receiving open-issues on github.

If a issue is opened on github the author should acknowledge having read the issue by responding to it
with some comment.
The acknowledgement comment may also be used to 'make questions/make comments' about the issue.
A comment indicating a possible solution for the issue before actually fixing the issue is welcomed.
After making the commit fixing the issue, make a comment to indicate that the issue is fixed, 
but don't close the issue yourself, let the staff member close it if he really think the issue is solved.

It is also a good practice to open issues yourself for problems you identify. 
If you are having problem solving an issue by yourself try asking for help from staff members.



## Description

 First, let's create a basic layout with buttons and interactions.

## Objectives
  - App layout needs to have :

     - Main screen of the application:
          - a button with id = "log_in_button"  that points to log in screen,
          - a button with id =  "sign_up_button" that points to sign up screen,
          
     - Log in screen :
          - Username input field with id = "username_input,
          - password input field (only numbers allowed) with id = "password_input",
          - button with id = "log_in_button"
          
      - Sign up screen (input fields) :
        - Firstname with id = "firstname_input",
        - Lastname with id = "lastname_input",
        - Address with id = "address_input" ,
        - Phone number with id = "phone_number_input",
        - Username with id = "username_input",
        - password (only numbers allowed) with id = "password_input".
        - button with id = "sign_up_button"
