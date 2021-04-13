import React, { createRef, SyntheticEvent, useEffect } from "react";
import { shallowEqual, useDispatch, useSelector } from "react-redux";
import { RouteComponentProps, withRouter } from 'react-router-dom';
import { Grid, Pagination, Ref, Sticky } from "semantic-ui-react";
import CategoriesList from "../../components/main/CategoriesList";
import ProductsList from "../../components/main/ProductsList";
import { RootState } from "../../modules";
import { initializeProducts, loadCategories, loadProducts, like, unLike } from "../../modules/main";
import queryString from "query-string";
import storage from "../../lib/storage";


interface Props extends RouteComponentProps{

}

const MainContainer: React.FC<Props> = ({ history, match, location }) => {

  // const { id }: { id?: string } = match.params;
  
  const dispatch = useDispatch();
  // const contextRef = createRef<HTMLElement>();
  
  const { category } : { category? : string} = match.params;
  const query = queryString.parse(location.search);


  const { loading, categories, products, pageable, error } = useSelector(
    (state: RootState) => ({
      loading: state.loading["main/LOAD_PRODUCTS"],
      categories: state.main.categories,
      products: state.main.products,
      pageable: state.main.pageable,
      error: state.main.error,
    }),
    shallowEqual
  );


  // useEffect(()=>{
  //   console.log(loading);
  // },[loading])

  useEffect(()=>{
    dispatch(initializeProducts());
    dispatch(loadProducts.request({ page:query.page, type: query.type }));
  },[query.type, query.page]);




  useEffect(()=>{

    // query.like
    if(!categories){
      dispatch(loadCategories.request());
    }
    // dispatch(loadProducts.request(id));
    if(!products){
      dispatch(loadProducts.request({page: query.page ? query.page : null, type: query.type}));
    }
  },[dispatch]);
  



  // useEffect(() => {
  //   // scroll event listener 등록
  //   window.addEventListener("scroll", handleScroll);
  //   return () => {
  //     // scroll event listener 해제
  //     window.removeEventListener("scroll", handleScroll);
  //   };
  // });
 

  const handlePaging = (event: SyntheticEvent, data: any) => {
    const page = data.activePage;
    if(!query.type){
      history.push(`/main?page=${page}`);
    }else{
      history.push(`/main?type=${query.type}&page=${page}`);
    }
  }



  const handleScroll = () => {
    const scrollHeight = document.documentElement.scrollHeight;
    const scrollTop = document.documentElement.scrollTop;
    const clientHeight = document.documentElement.clientHeight;

    // console.log("hi", pageable)

    if (scrollTop + clientHeight >= scrollHeight && !loading) {
      // 페이지 끝에 도달하면 추가 데이터를 받아온다
      // console.log('more data')
      // console.log(products, pageable);
      
      dispatch(loadProducts.request({type: query.type, page: pageable.pageNumber + 2}));
    }
  };


  const handleToggleLike = (id: number) => {
    console.log(pageable);
    const idx = products ? products.findIndex(product=>product.id === id) : -1;
    if(products && idx > -1 && !products[idx].isLike){
      dispatch(like.request(id));
    }else{
      dispatch(unLike.request(id));

      var ddd = <div>dd</div>;
    }
  }

  return (
    <Grid stackable>
      <Grid.Row>
      
        <Grid.Column width={4}>
            {/* <Sticky context={contextRef}> */}
              <CategoriesList categories={categories} />
            {/* </Sticky> */}
        </Grid.Column>
      
        <Grid.Column width={12}>
          <ProductsList
            loading={loading}
            products={products}
            pageable={pageable}
            handleToggleLike={handleToggleLike}
            handlePaging={handlePaging}
          />
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
};



export default withRouter(MainContainer);
