/**
 * MType
 */

var UI = require('ui');


var hash=  {".-": "a", 
                        "-...":"b",
                        "-.-.":"c",
                        "-..":"d",
                        ".":"e",
                        "..-.":"f",
                        "--.":"g",
                        "....":"h",
                        "..":"i",
                        ".---":"j",
                        "-.-":"k",
                        ".-..":"l",
                        "--":"m",
                        "-.":"n",
                        "---":"o",
                        ".--.":"p",
                        "--.-":"q",
                        ".-.":"r",
                        "...":"s",
                        "-":"t",
                        "..-":"u",
                        "...-":"v",
                        ".--":"w",
                        "-..-":"x",
                        "-.--":"y",
                        "--..":"z"};


// Create a Card with title and subtitle
var card = new UI.Card({
  title:'MType',
  subtitle:'Tap Away...'
});

// Message
var message = "";
var morseMessage = '';

var selectCount = 0;

card.on('click', function(e) {
  // Button Detection
  if(e.button == 'down'){
    // Dot
    selectCount = 0;
    morseMessage += '.';
  }
  else if(e.button == 'up'){
    // Dash
    selectCount = 0;
    morseMessage += '-';
  }
  else if(e.button == 'select' && selectCount == 1){
    //Space
    selectCount = 0;
    message += " ";
    morseMessage = "";
  }
  else if(e.button == 'select' && selectCount === 0){
    //Done Letter
    selectCount = 1;
    message += (hash[morseMessage] != undefined)? hash[morseMessage] : "";
    morseMessage = "";
  }
  else if (e.button == 'back'){
    //Send Message
    selectCount = 0;
  }

  card.subtitle(morseMessage+" ");
  card.body (message);
  
});


// Display the Card
card.show();

