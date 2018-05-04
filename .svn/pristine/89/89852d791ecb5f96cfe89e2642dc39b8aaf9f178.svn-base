<script type="text/javascript">
	$.ajax({
   		url: "../jcsj/table_head!showmodellist.action?headpid=${headpid}&isshow=0",
   		type: "post",
   		success: function(text){
   			var data = mini.clone(text);
            data = mini.decode(data);
            for(var i=0;i<data.length;i++){
            	//alert(data[i].physicname)
          		 grid.hideColumn(data[i].physicname);
            }

   		}
   	});
    
    $.ajax({
   		url: "../jcsj/table_head!showmodellist.action?headpid=${headfid}&isshow=0&show=true",
   		type: "post",
   		success: function(text){
   			var data = mini.clone(text);
            data = mini.decode(data);
            for(var i=0;i<data.length;i++){
            	//alert(data[i].physicname)
            	detail_grid.hideColumn(data[i].physicname);
            }

   		}
   	});
</script>