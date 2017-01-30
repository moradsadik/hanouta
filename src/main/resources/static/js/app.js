$(document).ready(function(){
	
	// ISOTOPE SETTINGS.
	var $grid = $('.produits').isotope({
		itemSelector : '.produit',
		layoutMode : 'masonry',
		stagger : 10,
	});
	
	// FILTER ISOTOPE.
	$('.product-item-filter').on('click', 'li', function(e){
		e.stopPropagation();
		$(this).siblings().removeClass('current');
		$(this).addClass('current');
		var $filter = $(this).attr('data-filter');
		$grid.isotope({filter : $filter });
	})
	
	$('.ui.dropdown').dropdown();
	$('.special.cards .image').dimmer({ on: 'hover' });
	
	// TRANSFORM INPUT FILE TO BUTTON.
	$('.upload-button').on('click',function(evt){
		$(this).siblings('.file-container').click();
	})



	// TAGS LIST.
	$('.tag-input').on("blur", function(e){
		var me = $(this);
		//13
		var cls = ['red','orange','yellow','olive','green','teal','blue','violet','purple','pink','brown','grey','black'];
		var input = me;
		var cible = me.parents('.tag-parent').siblings('.tag-list');
		cible.empty();
		
		var tags = input.val().split(',');
		var i = 0 ;
		$.each(tags,function(k,v){
			if(i>=cls.length){ i=0; }
			if(v != "") {
				cible.append('<div style="margin-top:7px; padding:7px;" class="ui '+cls[i]+' horizontal label">'+v+'</div>');
			}
			i++;
		})
		
	})
	
	//COLOR PICKER.
	var elem = $('.color-input');
	var huebee = new Huebee(elem[0],{
		customColors: [ '#C25', '#E62', '#EA0', '#19F', '#333' ]
	});
	elem.css({color :function(){ return $(this).val()}});
	elem.on('blur',function(){ $(this).css({color :$(this).val()}); });
	
	
});