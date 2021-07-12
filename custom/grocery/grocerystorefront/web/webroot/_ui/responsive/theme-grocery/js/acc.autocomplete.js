ACC.autocomplete = {

	_autoload: [
		"bindSearchAutocomplete",
        "bindDisableSearch"
	],

	bindSearchAutocomplete: function ()
	{
		// extend the default autocomplete widget, to solve issue on multiple instances of the searchbox component
		$.widget( "custom.yautocomplete", $.ui.autocomplete, {
			_create:function(){
				
				// get instance specific options form the html data attr
				var option = this.element.data("options");
				// set the options to the widget
				this._setOptions({
					minLength: option.minCharactersBeforeRequest,
					displayProductImages: option.displayProductImages,
					delay: option.waitTimeBeforeRequest,
					autocompleteUrl: option.autocompleteUrl,
					source: this.source
				});
				
				// call the _super()
				$.ui.autocomplete.prototype._create.call(this);
				
			},
			options:{
				cache:{}, // init cache per instance
				focus: function (){return false;}, // prevent textfield value replacement on item focus
				select: function (event, ui){
					ui.item.value = ACC.sanitizer.sanitize(ui.item.value);
                    window.location.href = ui.item.url;
                }
			},
			_renderItem : function (ul, item){
				
				if (item.type == "autoSuggestion"){
					var renderHtml = $("<a>").attr("href", item.url).addClass("menu-item-flex auto-suggest")
							.append($("<div>").addClass("name").text(item.value));
					return $("<li>")
							.data("item.autocomplete", item)
							.append(renderHtml)
							.appendTo(ul);
				}
				else if (item.type == "productResult"){
					var renderHtml = $("<a>").attr("href", item.url).addClass("menu-item-flex product-result")	
					.append(
									item.image  
											? $("<div>").addClass("thumb search-product-image")
													.append($("<img>").attr("src", item.image))
											: null
							)
							//.append($("<div>").addClass("name").html(ACC.sanitizer.sanitize(item.value)))
							.append("<div class='name search-item-custom'>"+ACC.sanitizer.sanitize(item.value)+"<br><div><span class='search-item-brand'>"+item.brand+"</span><span class='search-item-weight'> &nbsp;"+item.weight+"</span></div></div>")
							.append($("<div>").addClass("price").text(item.price));
					if(parseInt(item.price.substr(1,)) > 0){
					return $("<li>")
							.data("item.autocomplete", item)
							.append(renderHtml)
							.appendTo(ul);
					}else{
						return $("<li>")
                        .css("display","none")
                        .data("item.autocomplete", item)
                        .append(renderHtml)
                        .appendTo(ul);
					}
				
				}
			},
			source: function (request, response)
			{
				var self=this;
				var term = request.term.toLowerCase();
				if (term in self.options.cache)
				{
					return response(self.options.cache[term]);
				}

				$.getJSON(self.options.autocompleteUrl, {term: request.term}, function (data)
				{
					var autoSearchData = [];
					if(data.suggestions != null){
						$.each(data.suggestions, function (i, obj)
						{
							autoSearchData.push({
								value: obj.term,
								url: ACC.config.encodedContextPath + "/search?text=" + encodeURIComponent(obj.term),
								type: "autoSuggestion"
							});
						});
					}
					if(data.products != null){
						$.each(data.products, function (i, obj)
						{
							autoSearchData.push({
								value: ACC.sanitizer.sanitize(obj.name),
								code: obj.code,
								brand: obj.brand,
								weight: obj.weight,
								desc: ACC.sanitizer.sanitize(obj.description),
								manufacturer: ACC.sanitizer.sanitize(obj.manufacturer),
								url:  ACC.config.encodedContextPath + obj.url,
								price: obj.price != null ? (obj.price.formattedSalesPrice != null ? obj.price.formattedSalesPrice : (obj.price.formattedValue != null ? obj.price.formattedValue : 0.0)) : 0.0,
								type: "productResult",
								image: (obj.images!=null && self.options.displayProductImages) ? obj.images[0].url : null // prevent errors if obj.images = null
							});
						});
					}
					self.options.cache[term] = autoSearchData;
					return response(autoSearchData);
				});
			}

		});

	
		$search = $(".js-site-search-input");
		if($search.length>0){
			$search.yautocomplete()
		}

	},

	bindDisableSearch: function ()
    {
        $('#js-site-search-input').keyup(function(){
        	$('#js-site-search-input').val($('#js-site-search-input').val().replace(/^\s+/gm,''));
            $('.js_search_button').prop('disabled', this.value == "" ? true : false);
        })
    }
};