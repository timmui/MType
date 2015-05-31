/**
 * Welcome to Pebble.js!
 *
 * This is where you write your app.
 */

var UI = require('ui');
var ajax = require('ajax');
var user = 'Tim';

// Make a list of menu items
var messageList = [];

ajax(
  {
    url: 'http://ecetitanic-mtype.azure-mobile.net/api/mtype?user='+user,
  },
  function(data, status, request) {
    var temp = data.substring(2,data.length-2);
    
    var messages = temp.split (/},{/);
    
    console.log("len "+messages.length);
    console.log(messages[0]);
    console.log(messages[1]);
    console.log(messages[2]);
    console.log(messages[3]);
    
    for (var i = 0; i < messages.length; i++){
      var a = messages[i].substring(220,messages[i].length-1);
      var obj = a.split(/,|:|"/);
      console.log ("OBJ: "+obj[3]+" "+obj[9]);
      
      messageList.push({
        title: obj[9],
        subtitle: obj[3]
      });
    }
    console.log('Messages: '+ data);
    
    // Create the Menu, supplying the list of fruits
    var inboxMenu = new UI.Menu({
      sections: [{
        title: 'Messages',
        items: messageList
      }]
    });
    
    // Show the Menu
    inboxMenu.show();
  },
  function(error, status, request) {
    console.log('The ajax request failed: ' + error);
  }
);