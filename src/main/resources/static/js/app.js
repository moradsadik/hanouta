$(document).ready(function(){
	var $grid = $('.produits').isotope({
		itemSelector : '.produit',
		layoutMode : 'masonry',
		stagger : 10,
	});
	
	$('.product-item-filter').on('click', 'li', function(e){
		e.stopPropagation();
		$(this).siblings().removeClass('current');
		$(this).addClass('current');
		var $filter = $(this).attr('data-filter');
		$grid.isotope({filter : $filter });
	})
	
	$('.ui.dropdown').dropdown();
	$('.special.cards .image').dimmer({ on: 'hover' });
	
	
	$('.upload-button').on('click',function(evt){
		$(this).siblings('.file-container').click();
	})
	
	
	
	var preview = $('.media-preview');
	$('#produit-form').fileupload({
		url : '/hanouta/media/add/single',
		add : function(e,data){
			var send = data.submit();
		},
		progress : function(e,data){
		},
		done : function(e, data){
			console.log("done : ",data)
			var src = data.result.path;
			preview.append('<div class="card special cards"> <div class="blurring dimmable image"> <div class="ui dimmer"> <div class="content"> <div class="center"> <div class="ui red inverted button">Delete Image</div> </div> </div> </div> <img src="'+src+'" /> </div> <div class="extra content"> <button class="ui button red fluid"> <i class="remove icon"></i>Set As Default </button> </div> </div>');					
			
		},
		fail : function(e, data){}
			
	});
	
	
	
	
	
	
	
	
	

//	$('.form-produit').on('click', function(e){
//		e.preventDefault();
//		var fd = new FormData();
//		var submit = $(this);
//		var fm = $(this).parents('form');
//		var form = fm[0];
//		var file = $('.file-container');
//		var medias = file[0].files[0];
//		var preview = $('.media-preview');
//				
//		var produit = { name : "dell", titre:"Dell lenovo12", color:"black", description : "tres bon etat", size:"17", prix :12.30 } ;
//		var tags  = ['new','pc','pc','dell','informatique', 'electronique'] ;
//		
//		var list = []
//		$.each(file[0].files, function(i, f){
//			fd.append('files', f);
//			list.push(URL.createObjectURL(f));
//		})
//		
//		console.log(list);
//		fd.append('produit', JSON.stringify(produit));
//		fd.append('cles', JSON.stringify(tags));
//		
//		$.ajax({
//			type        : 'post',
//			data        : fd,
//			processData : false,
//			contentType : false,
//			url         : '/hanouta/produit/add/multiple',
//			success:function(d){ 
//				console.log(d) 
//				$.each(list, function(i,src){
//					preview.append('<div class="card special cards"> <div class="blurring dimmable image"> <div class="ui dimmer"> <div class="content"> <div class="center"> <div class="ui red inverted button">Delete Image</div> </div> </div> </div> <img src="'+src+'" /> </div> <div class="extra content"> <button class="ui button red fluid"> <i class="remove icon"></i>Set As Default </button> </div> </div>');					
//				})
//			},
//			error : function(err){ console.log(err)}
//		});
//				
//	})
	
	//UPLOAD MULTIPLE FILE.


	// ADD TAGS
	$('.add-tags').on('click', function(e){
		var me = $(this);
		//13
		var cls = ['red','orange','yellow','olive','green','teal','blue','violet','purple','pink','brown','grey','black'];
		var input = me.siblings('input');
		var cible = me.parents('.tag-parent').siblings('.tag-list');
		cible.empty();
		console.log(cible.html());
		
		var tags = input.val().split(',');
		var i = 0 ;
		$.each(tags,function(k,v){
			if(i>=cls.length){ i=0; }
			if(v != "") {
				console.log(v,cls[i],i);
				cible.append('<div style="margin-top:5px" class="ui '+cls[i]+' horizontal label">'+v+'</div>');
			}
			i++;
		})
		
	})
	
	
});