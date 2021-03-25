import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RouteComponentProps, withRouter } from 'react-router-dom';
import { Grid } from "semantic-ui-react";
import ProductsView from "../../components/view/ProductsView";
import { RootState } from "../../modules";
import {
  loadProduct,
  loadComments,
  registerComment,
  updateComment,
  deleteComment,
  changeCommentField,
  toggleCommentMode,
} from "../../modules/product";



interface Props extends RouteComponentProps{
  
}

const ProductsViewContainer: React.FC<Props> = ({
  match,
  history
}) => {

  const dispatch = useDispatch();
  const { id } : { id? : number} = match.params;
  

  const {
    categories,
    product,
    form,
    comments,
    error,
  } = useSelector(
    (state: RootState) => ({
      categories: state.main.categories,
      product: state.product.product,
      form: state.product.form,
      comments: state.product.comments,
      error: state.product.error,
    })
  );
  

  useEffect(()=>{
    dispatch(loadProduct.request(id));
    dispatch(loadComments.request(id));
  },[dispatch]);


  const handleChangeComment = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    dispatch(changeCommentField({ key: name, value }));
  };

  const handleChangeAuction = () => {
      
  }


  const handleRegisterComment =  () => {
    // registerComment
    const { content } = form.comments;
    dispatch(registerComment.request({id, content}));

  }

  const handleToggleCommentMode = (id: number) => {
    console.log(id);
    dispatch(toggleCommentMode(id));

  }

  const handleUpdateComment = (cid: number) => {
    const { update : content } = form.comments;
    dispatch(updateComment.request({id, cid, content}));
  }

  const handleDeleteComment = (cid: number) => {
    dispatch(deleteComment.request({id, cid}));
  }

  const handleToggleLike = () => {

  }

  const handleEnterAuction = () => {

  }

  const handleCancelAuction = () => {

  }

  


  return (
    <Grid>
      <Grid.Row columns="1">
        <Grid.Column>
          {product && (
            <ProductsView
              product={product}
              form={form}
              comments={comments}
              handleChangeComment={handleChangeComment}
              handleToggleCommentMode={handleToggleCommentMode}
              handleRegisterComment={handleRegisterComment}
              handleUpdateComment={handleUpdateComment}
              handleDeleteComment={handleDeleteComment}
              handleToggleLike={handleToggleLike}
              handleEnterAuction={handleEnterAuction}
              handleCancelAuction={handleCancelAuction}
            />
          )}
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
};

export default withRouter(ProductsViewContainer);
