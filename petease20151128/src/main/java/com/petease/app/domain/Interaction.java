package com.petease.app.domain;
public class Interaction {
       private Post article;
       private Comment comment;
       private Rate rate;
       public Interaction(){
         article=new Post();
         comment=new Comment();
         rate=new Rate();
        }
        public void setArticle(Post article) {
		this.article = article;
	}
        public Post getArticle() {
		return this.article;
	}
        public void setComment(Comment comment) {
		this.comment = comment;
	}
        public Comment getComment() {
		return this.comment;
	}
        public void setRate(Rate rate) {
		this.rate = rate;
	}
        public Rate getRate() {
		return this.rate;
	}
        public String toString() {
            return  "article = [" + this.article.toString() + "]"+
                    ", comment = [" + this.comment.toString() + "]"+
                    ", rate = [" + this.rate.toString() + "]";
        }

        //equals
        @Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Interaction)) {
			return false;
		}
		Interaction interactionObj = (Interaction) obj;
              return  (this.article.equals(interactionObj.article))&&
                        (this.comment.equals(interactionObj.comment))&&
                        (this.rate.equals(interactionObj.rate));
        }
        //clone
	
         @Override
	
        public Interaction clone() {
		
               Interaction interactionObj = new Interaction();
		
               interactionObj.article.clone();
		
	       interactionObj.rate.clone();

                  return interactionObj;
	}

}