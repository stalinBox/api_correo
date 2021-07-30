<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Spring boot send mail example</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	
	<style>
		div.imgs{
	  		width: 65%;
	  		white-space: nowrap;
  	  		text-overflow: ellipsis;
  	  		overflow: hidden;
		}
	</style>
  </head>
  <body>
  
  <div class="header imgs">
  	<img src='cid:header' alt="" style="width:100%;"/>
  	<hr>
  </div>
  
  <div class="container">
  	<spam>Estimado/a: ${destinatarioNombre} </spam>
  	<div class="row" >
    	<spam>Proyecto | Sistema: ${proyectoNombre}</spam>
  	</div>
	<br>
	<div class="row">
		${contenido}
	</div>
  </div>
  <br>
  <br>
  <br>
  
  <div class="row">
  <spam>Saludos Cordiales,</spam>
  <br>
  <br>
  <strong><spam>Equipo de Soporte</spam></strong>
  </div>
  <br>
  
  <div class="footer imgs">
  	<div class="row"> 
  		<strong><spam>Ministerio de Agricultura y Ganadería</spam></strong>
  	</div>
  	<div class="row">Av. Eloy Alfaro N30-350 y Av. Amazonas</div>
  	<div class="row">Telf.: + (593 2) 3960 100 • Ext 1093</div>
  	<div class="row">
  	Web: 
  		<a href="https://www.agricultura.gob.ec">
			www.agricultura.gob.ec
		</a>
  	</div>
  	<div class="row">Quito - Ecuador.</div>
  	<img src='cid:footer' alt="" style="width:100%;"/>
  	<br>
  	<span>No responder,</span><br>
  	<span>Este mensaje que ha sido emitido automáticamente por el Sistema Central del Ministerio de Agricultura y Ganadería.</span>
  </div>
  

  </body>
</html>