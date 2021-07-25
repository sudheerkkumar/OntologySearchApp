import * as React from 'react';
import TableComponent from './TableComponent.js';

const initialState = {
    searchParam: '',
    found: false,
    ontologyId: '',
    title : '',
    description: '',
    definitionProperties: [],
    synonymProperties: []
}
export default class SearchComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = initialState;
        this.handleChange = this.handleChange.bind(this);
        this.getOntologies = this.getOntologies.bind(this);
        
      }

   
  handleChange(event) {
    this.setState({searchParam: event.target.value});
  }

  updateState(result){
        this.setState({
            found: true,
            ontologyId: result.ontologyId,
            title:result.title,
            description: result.description,
            definitionProperties: result.definitionProperties,
            synonymProperties: result.synonymProperties
        });
  }
  /*componentDidMount(){
    const url = "http://localhost:8888/api/getOntology/mondo";
    fetch(url).then(res => {
        console.log(res);
        if(!res.ok){
            this.setState({found: false});
        }else{
           return res.json()
        }
        }).then(result => this.updateState(result));
  }*/

  getOntologies(){
      var url = "http://localhost:8888/api/getOntology/"+this.state.searchParam;
      fetch(url).then(async response => {
        const data = await response.json();
        if(!response.ok){
            const query= this.state.searchParam;
            this.setState(initialState);
            this.setState({searchParam: query});
            // get error message from body or default to response statusText
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }else{
           return data;
        }
        }).then(result => this.updateState(result));
  }

  render() {
            return(
                <div>
                <input type="text" placeholder="Search Ontology...(ex.,mondo,efo)" value={this.state.searchParam} onChange={this.handleChange} />
                <button onClick={this.getOntologies}><i class="fa fa-search"></i></button>
                {!this.state.found ? (<h2 id="ontologies">No Ontologies ...</h2>) : '' }
                <TableComponent ontologyId={this.state.ontologyId}
                                title={this.state.title}
                                description={this.state.description}
                                definitionProperties={this.state.definitionProperties}
                                synonymProperties={this.state.synonymProperties}
                                />
                </div>
            );
        
  }
}