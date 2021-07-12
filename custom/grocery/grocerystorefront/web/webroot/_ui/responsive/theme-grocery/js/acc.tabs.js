ACC.tabs = {

	_autoload: [
		"bindTabs",
		"hideReviewBtn",
		"determineToDisplayReviews"
	],

	bindTabs: function(){
		
		$(document).on("click", '.js-writeReviewTab', function(e){
			e.preventDefault();
			$(".js-review-write").show();
			$('#reviewForm input[name=headline]').focus();
		});
		
		$(document).on("click",".all-reviews-btn",function(e){
			e.preventDefault();
			ACC.tabs.showReviewsAction("allreviews");
			ACC.tabs.hideReviewBtn(".all-reviews-btn");
			ACC.tabs.showReviewBtn(".less-reviews-btn");
		});
		
		$(document).on("click",".less-reviews-btn",function(e){
			e.preventDefault();
			ACC.tabs.showReviewsAction("reviews");
			ACC.tabs.hideReviewBtn(".less-reviews-btn");
			ACC.tabs.showReviewBtn(".all-reviews-btn");
		});
		
		$(document).on("click",".js-review-write-toggle",function(e){
			e.preventDefault();
			if($(".js-review-write:visible").length<1){
				$(".js-review-write").show();

			}else{
				$(".js-review-write").hide();
			}		
		});
	},
	
	showReviewsAction: function (s)
	{
		$.get($("#reviews").data(s), undefined, function (result){
			$('#reviews').html(ACC.sanitizer.sanitize(result));
			if($(".js-ratingCalc").length > 0){
				ACC.ratingstars.bindRatingStars();
				ACC.tabs.showingAllReviews();
			}
		}, 'html');
	},
	
	hideReviewBtn: function (btnClass) {

		btnClass = (btnClass === undefined) ? ".less-reviews-btn" : btnClass;
		$(btnClass).hide();
	},
	
	showReviewBtn: function (btnClass) {
		$(btnClass).show();
	},
	
	showingAllReviews: function()
	{
		var isShowingAllReviews = $("#showingAllReviews").data("showingallreviews");
		if(isShowingAllReviews){
			ACC.tabs.hideReviewBtn(".all-reviews-btn");
		}
	},
	
	determineToDisplayReviews: function ()
	{
		if($("#reviews").length>0){
			ACC.tabs.showReviewsAction('reviews');
		}
	}
};
