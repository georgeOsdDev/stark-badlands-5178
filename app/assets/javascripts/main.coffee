$ ->
  limit = 140
  warning = 10

  counter = ->
    count = limit - $("#msg").val().length
    $('#count').text count
    if 0 < count < warning
      $('#count').css 'color', 'orange'
    else if count <= 0
      $('#count').css 'color', 'red'
    else
      $('#count').css 'color', 'black'    
        
  $('#msg').keyup ->
    counter()

  counter()