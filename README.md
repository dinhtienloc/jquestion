# JQuestion [![Build Status](https://travis-ci.org/dinhtienloc/jquestion.svg?branch=master)](https://travis-ci.org/dinhtienloc/jquestion)
A small Java library that provides some simple interactive CLI components for collecting user input

# Overview
I was very impressed by the console interface when I use JHipster to create a new project. I found that it uses
[Inquirer.js](https://github.com/SBoudrias/Inquirer.js) to handle the console with interactive CLI behind the scene. I can't find any 
libraries similar to Inquirer.js except [Console UI](https://github.com/awegmann/consoleui). However, Console UI is 
very low-maintenance and has some issue when running on Window. Therefore, I decide to create a new library with better API to use.
# Usage
#### Initialization
Before using JQuestion, you have to install AnsiConsole to standard Java's System stream:

    AnsiConsole.systemInstall();
    
JQuestion is using JLine3. JQuestion takes the `LineReader` instance from JLine and uses it to read user input. You can 
still use `LineReader` normally in other places without `JQuestion`:

    Terminal terminal = TerminalBuilder.builder()
                            .system(true)
                            .encoding(StandardCharsets.UTF_8)
                            .jansi(true)
                            .build();
    LineReader lr = LineReaderBuilder.builder().terminal(terminal).build();
    JQuestion jQuestion = JQuestion.initialize(lr);
    
`JQuestion` is a singleton instance. Once you initialize, you can easily call it via `JQuestion.instance()`.
#### Components
##### Input
    String answer = jQuestion.input("title").prompt();
##### Selection
    String answer = jQuestion.select("title", new String[]{"foo", "bar"}).prompt();
##### Confirmation
    Boolean answer = jQuestion.confirm("title").prompt();

