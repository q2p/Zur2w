(function(name,data){
 if(typeof onTileMapLoaded === 'undefined') {
  if(typeof TileMaps === 'undefined') TileMaps = {};
  TileMaps[name] = data;
 } else {
  onTileMapLoaded(name,data);
 }})("32",
{ "height":32,
 "layers":[
        {
         "data":[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2],
         "height":32,
         "name":"collision",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":32,
         "x":0,
         "y":0
        }, 
        {
         "data":[6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 6, 6, 12, 13, 14, 11, 12, 13, 6, 6, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 6, 6, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 6, 6, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 6, 6, 8, 9, 10, 6, 6, 6, 6, 6, 8, 9, 10, 7, 8, 6, 6, 6, 6, 9, 10, 7, 8, 9, 6, 6, 6, 6, 6, 7, 8, 9, 6, 6, 12, 13, 14, 6, 6, 6, 6, 11, 12, 13, 14, 11, 12, 13, 6, 6, 12, 13, 14, 11, 12, 13, 14, 6, 6, 6, 6, 11, 12, 13, 6, 6, 16, 17, 18, 6, 6, 6, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 6, 6, 6, 15, 16, 17, 6, 6, 20, 21, 22, 6, 6, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 6, 6, 19, 20, 21, 6, 6, 8, 9, 10, 6, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 6, 7, 8, 9, 6, 6, 12, 13, 14, 11, 12, 13, 14, 11, 6, 6, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 6, 6, 14, 11, 12, 13, 14, 11, 12, 13, 6, 6, 16, 17, 18, 15, 16, 17, 18, 15, 6, 6, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 6, 6, 18, 15, 16, 17, 18, 15, 16, 17, 6, 6, 6, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 6, 6, 6, 6, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 6, 6, 6, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 6, 6, 16, 17, 18, 6, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 6, 15, 16, 17, 6, 6, 20, 21, 22, 6, 6, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 6, 6, 19, 20, 21, 6, 6, 8, 9, 10, 6, 6, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 6, 6, 7, 8, 9, 6, 6, 12, 13, 14, 6, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 6, 11, 12, 13, 6, 6, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 6, 6, 6, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 6, 6, 6, 6, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 6, 6, 6, 12, 13, 14, 11, 12, 13, 14, 11, 6, 6, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 6, 6, 14, 11, 12, 13, 14, 11, 12, 13, 6, 6, 16, 17, 18, 15, 16, 17, 18, 15, 6, 6, 18, 15, 16, 17, 18, 15, 16, 17, 18, 15, 6, 6, 18, 15, 16, 17, 18, 15, 16, 17, 6, 6, 20, 21, 22, 6, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 22, 19, 20, 21, 6, 19, 20, 21, 6, 6, 8, 9, 10, 6, 6, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 6, 6, 7, 8, 9, 6, 6, 12, 13, 14, 6, 6, 6, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 6, 6, 6, 11, 12, 13, 6, 6, 16, 17, 18, 6, 6, 6, 6, 15, 16, 17, 18, 15, 16, 17, 6, 6, 16, 17, 18, 15, 16, 17, 18, 6, 6, 6, 6, 15, 16, 17, 6, 6, 20, 21, 22, 6, 6, 6, 6, 6, 20, 21, 22, 19, 20, 6, 6, 6, 6, 21, 22, 19, 20, 21, 6, 6, 6, 6, 6, 19, 20, 21, 6, 6, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 10, 7, 8, 9, 6, 6, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 14, 11, 12, 13, 6, 6, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 6, 6, 16, 17, 18, 15, 16, 17, 6, 6, 16, 17, 18, 15, 16, 17, 18, 15, 16, 17, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6],
         "height":32,
         "name":"graphics",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":32,
         "x":0,
         "y":0
        }, 
        {
         "data":[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 135, 0, 0, 0, 0, 133, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 139, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 133, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 135, 0, 0, 0, 0, 133, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 133, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 135, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 136, 0, 0, 0, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 135, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 138, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 133, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 135, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 133, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         "height":32,
         "name":"stuff",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":32,
         "x":0,
         "y":0
        }, 
        {
         "data":[79, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 80, 79, 75, 75, 75, 75, 75, 75, 80, 79, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 80, 77, 74, 72, 72, 72, 72, 72, 72, 72, 72, 72, 85, 84, 74, 72, 72, 72, 72, 72, 85, 84, 74, 72, 72, 72, 72, 72, 72, 72, 72, 72, 78, 77, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69, 72, 71, 0, 0, 0, 0, 0, 69, 72, 71, 0, 0, 0, 0, 0, 0, 0, 0, 0, 78, 77, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 78, 77, 73, 0, 0, 87, 76, 76, 86, 0, 70, 0, 0, 0, 0, 0, 87, 86, 0, 70, 0, 0, 0, 0, 0, 87, 76, 76, 86, 70, 0, 0, 78, 77, 73, 0, 0, 78, 83, 79, 84, 74, 71, 0, 0, 0, 0, 69, 85, 84, 74, 71, 0, 0, 0, 0, 69, 85, 80, 83, 77, 73, 0, 0, 78, 77, 73, 0, 0, 78, 79, 84, 74, 71, 0, 0, 0, 0, 0, 0, 69, 72, 71, 0, 0, 0, 0, 0, 0, 69, 85, 80, 77, 73, 0, 0, 78, 77, 73, 0, 0, 85, 84, 74, 71, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69, 85, 84, 73, 0, 0, 78, 77, 73, 0, 0, 0, 74, 71, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69, 0, 73, 0, 0, 78, 77, 73, 0, 0, 69, 71, 0, 0, 0, 87, 86, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 87, 86, 70, 0, 0, 0, 69, 71, 0, 0, 78, 77, 73, 0, 0, 0, 0, 0, 0, 0, 85, 84, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 85, 84, 73, 0, 0, 0, 0, 0, 0, 0, 78, 81, 86, 70, 0, 0, 0, 0, 0, 0, 69, 72, 71, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69, 72, 71, 0, 0, 0, 0, 0, 0, 87, 82, 79, 84, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 85, 80, 77, 74, 71, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69, 78, 77, 73, 0, 0, 0, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 70, 0, 0, 78, 77, 73, 0, 0, 87, 86, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 87, 86, 73, 0, 0, 78, 77, 73, 0, 0, 85, 84, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 85, 84, 73, 0, 0, 78, 77, 73, 0, 0, 0, 74, 71, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69, 0, 73, 0, 0, 78, 77, 73, 0, 0, 69, 71, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69, 71, 0, 0, 78, 81, 86, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 87, 82, 79, 84, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 85, 80, 77, 74, 71, 0, 0, 0, 0, 0, 0, 87, 86, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 87, 86, 70, 0, 0, 0, 0, 0, 0, 69, 78, 77, 73, 0, 0, 0, 0, 0, 0, 0, 85, 84, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 85, 84, 73, 0, 0, 0, 0, 0, 0, 0, 78, 77, 73, 0, 0, 0, 70, 0, 0, 0, 69, 72, 71, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69, 72, 71, 0, 0, 0, 0, 70, 0, 0, 78, 77, 73, 0, 0, 87, 86, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 87, 86, 73, 0, 0, 78, 77, 73, 0, 0, 78, 81, 86, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 87, 82, 77, 73, 0, 0, 78, 77, 73, 0, 0, 78, 83, 81, 86, 70, 0, 0, 0, 0, 0, 0, 87, 86, 70, 0, 0, 0, 0, 0, 0, 87, 82, 83, 77, 73, 0, 0, 78, 77, 73, 0, 0, 85, 75, 75, 84, 0, 70, 0, 0, 0, 0, 0, 85, 84, 0, 70, 0, 0, 0, 0, 0, 85, 75, 75, 84, 73, 0, 0, 78, 77, 73, 0, 0, 69, 72, 72, 72, 72, 71, 0, 0, 0, 0, 69, 72, 72, 72, 71, 0, 0, 0, 0, 69, 72, 72, 72, 72, 71, 0, 0, 78, 77, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 78, 77, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 87, 86, 70, 0, 0, 0, 0, 0, 87, 86, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 78, 81, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 82, 81, 76, 76, 76, 76, 76, 76, 82, 81, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 82],
         "height":32,
         "name":"overlay",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":32,
         "x":0,
         "y":0
        }, 
        {
         "draworder":"topdown",
         "height":32,
         "name":"spawnPoints",
         "objects":[
                {
                 "height":0,
                 "id":1,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":48,
                 "y":48
                }, 
                {
                 "height":0,
                 "id":2,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":48,
                 "y":464
                }, 
                {
                 "height":0,
                 "id":3,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":464,
                 "y":464
                }, 
                {
                 "height":0,
                 "id":4,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":464,
                 "y":48
                }, 
                {
                 "height":0,
                 "id":5,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":48,
                 "y":256
                }, 
                {
                 "height":0,
                 "id":6,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":464,
                 "y":256
                }, 
                {
                 "height":0,
                 "id":7,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":256,
                 "y":464
                }, 
                {
                 "height":0,
                 "id":8,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":256,
                 "y":48
                }],
         "opacity":1,
         "type":"objectgroup",
         "visible":true,
         "width":32,
         "x":0,
         "y":0
        }, 
        {
         "draworder":"topdown",
         "height":32,
         "name":"dropPoints",
         "objects":[
                {
                 "height":0,
                 "id":9,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":256,
                 "y":128
                }, 
                {
                 "height":0,
                 "id":12,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":256,
                 "y":384
                }, 
                {
                 "height":0,
                 "id":13,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":128,
                 "y":256
                }, 
                {
                 "height":0,
                 "id":14,
                 "name":"",
                 "properties":
                    {

                    },
                 "rotation":0,
                 "type":"",
                 "visible":true,
                 "width":0,
                 "x":384,
                 "y":256
                }],
         "opacity":1,
         "type":"objectgroup",
         "visible":true,
         "width":32,
         "x":0,
         "y":0
        }],
 "nextobjectid":15,
 "orientation":"orthogonal",
 "properties":
    {

    },
 "renderorder":"left-up",
 "tileheight":16,
 "tilesets":[
        {
         "firstgid":1,
         "image":"..\/..\/Zur2w\/unuse\/tiles.png",
         "imageheight":16,
         "imagewidth":64,
         "margin":0,
         "name":"tiles",
         "properties":
            {

            },
         "spacing":0,
         "tilecount":4,
         "tileheight":16,
         "tilewidth":16
        }, 
        {
         "firstgid":5,
         "image":"tilesNormal.png",
         "imageheight":48,
         "imagewidth":1024,
         "margin":0,
         "name":"tilesNormal",
         "properties":
            {

            },
         "spacing":0,
         "tilecount":192,
         "tileheight":16,
         "tilewidth":16
        }],
 "tilewidth":16,
 "version":1,
 "width":32
});