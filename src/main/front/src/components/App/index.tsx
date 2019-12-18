import React from 'react';
import './App.less';
import Header from '../Header';
import Layout from '../Layout';
import Footer from '../Footer';
import Container from '@material-ui/core/Container';

const App: React.FC = () => (
  <div className="App-root">
    <Header/>
    <section className="App-main">
        <Container maxWidth="sm">
            <Layout/>
        </Container>
    </section>
    <Footer/>
  </div>
);

export default App;
