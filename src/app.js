/**
 * MType
 */
var ajax = require('ajax');
var UI = require('ui');
// Import the Vibe object
var Vibe = require('ui/vibe');
var Settings = require('settings');

var sender = 'Pebble';
var receiver = 'Jhony';

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
  else if(e.button == 'select' && selectCount == 2){
    //Send Message
    /************************************* NETWORKING *******************************************/

     ajax(
        {
          url: 'http://ecetitanic-mtype.azure-mobile.net/api/mtype?text='+message+'&sender='+sender+'&receiver='+receiver,
          method: 'post'
        },
        function(data, status, request) {
          console.log('Messages: ' );
        },
        function(error, status, request) {
          console.log('The ajax request failed: ' + error);
        }
    
     );
    // Trigger a vibration
    Vibe.vibrate('short');
    
    selectCount = 0;
    message = "";
    morseMessage = "";

  }
  else if(e.button == 'select' && selectCount == 1){
    //Space

    selectCount = 2;
    message += " ";
    morseMessage = "";
  }
  else if(e.button == 'select' && selectCount === 0){
    //Done Letter
    selectCount = 1;
    message += (hash[morseMessage] !== undefined)? hash[morseMessage] : "";
    morseMessage = "";
    
  }
  // Update UI
  card.subtitle(morseMessage+" ");
  card.body (message);
  
});

// Display the Card
card.show();

