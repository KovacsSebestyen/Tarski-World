import { useState } from "react"

function App() {
  const [formula, setFormula] = useState("")
  const [backendresponse, setBackendResponse] = useState(null)
  const [shapes, setShapes] = useState([])
  const [shape, setShape] = useState({"pos_x": 1, "pos_y": 2, "shape": "Triangle", "color": "Blue", "size": "Small", "name": "a"})
  const iframeString = '<iframe width="560" height="315" src="https://www.youtube.com/embed/L8XbI9aJOXk?si=seRWZYiIMash7OYK" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>';

  const handleChange = (event) => {
    const value = event.target.value
    setFormula(value)
    console.log(formula)
  }

  const changePosX = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.pos_x = value
    setShape({...localshape})
  }

  const changePosY = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.pos_y = value
    setShape({...localshape})
  }

  const changeShape = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.shape = value
    setShape({...localshape})
  }

  const changeColor = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.color = value
    setShape({...localshape})
  }

  const changeSize = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.size = value
    setShape({...localshape})
  }

  const changeName = (event) => {
    const value = event.target.value
    var localshape = shape
    localshape.name = value
    setShape({...localshape})
  }

  const addShape = (event) => {
    var localshapes = shapes
    localshapes.push(shape)
    setShapes([...localshapes])
  }

  const handleSubmit = async (event) => {
    console.log(shapes)
    event.preventDefault()
    fetch("/tarski/validate", {
        method: "POST", 
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(
            {
                "shapes": shapes,
                "formula": formula
            }
        ),
      }).then(response => {
        return response.text();
      })
      .then(data => {
        setBackendResponse(data)
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
      
    }

  return (
    <div>
      <div className="green_background">
        <h1 id="tarski_title">Tarski világa</h1>
      </div>
      <div className="Create_Shape">
        <div className="green_background">
          <h2 align="center">Elem létrehozása</h2>
        <form align="center">
          <label >
          X pozíció:
            <input className="position_input" type="number" id="position_x" name="position_x" onChange={changePosX}>
            </input>
          </label>
          <label >
          Y pozíció:
            <input className="position_input" type="number" id="position_y" name="position_y" onChange={changePosY}>
            </input>
          </label>
        </form>
        <div className="radio_buttons">
        <div className="create_column">
            <label>
              <input type="radio" value="Triangle" id="triangle" name="shape" defaultChecked onChange={changeShape}>
              </input>
              Triangle
           </label><br/>
            <label>
             <input type="radio" value="Square" id="square" name="shape" onChange={changeShape}>
             </input>
             Square
           </label><br/>
           <label>
             <input type="radio" value="Circle" id="circle" name="shape" onChange={changeShape}>
              </input>
              Circle
            </label><br/>
        </div>
        <div className="create_column">
          <label>
            <input type="radio" value="Red" id="red" name="color" defaultChecked onChange={changeColor}>
            </input>
            Red
          </label><br/>
          <label>
            <input type="radio" value="Green" id="green" name="color" onChange={changeColor}>
            </input>
            Green
          </label><br/>
          <label>
            <input type="radio" value="Blue" id="blue" name="color" onChange={changeColor}>
            </input>
            Blue
          </label><br/>
        </div>
        <div id="float_right">
          <label>
            <input type="radio" value="Small" id="small" name="size" defaultChecked onChange={changeSize}>
            </input>
            Small
          </label><br/>
          <label>
            <input type="radio" value="Medium" id="medium" name="size" onChange={changeSize}>
            </input>
            Medium
          </label><br/>
          <label>
            <input type="radio" value="Large" id="large" name="size" onChange={changeSize}>
            </input>
            Large
          </label><br/>
        </div>
        </div>
        <div align="center">
        <form>
          <label>
            Name:
          <input className="input" type="text" id="shape_name" name="shape_name" onChange={changeName}>
          </input>
          </label>
        </form>
        <button type="button" onClick={addShape}>Add shape!</button>
        </div>
        </div>
        <div className="green_background" align="center">
          <h2>Létrehozandó alakzat</h2>
          <table>
            <tr id="table_title">
              <td>X pozíció</td>
              <td>Y pozíció</td>
              <td>Alakzat</td>
              <td>Szín</td>
              <td>Méret</td>
              <td>Név</td>
            </tr>
            <tr id="table_rows">
              <td>{shape.pos_x}</td>
              <td>{shape.pos_y}</td>
              <td>{shape.shape}</td>
             <td>{shape.color}</td>
              <td>{shape.size}</td>
              <td>{shape.name}</td>
            </tr>
          </table>
        </div>
      </div>
      <div className="Send_Formula">
        <div className="green_background">
        <header className="App-header" align="center">
          <h2>Formula megadása</h2>
          <form>
            <label>
              Formula:
            <input className="input" type="text" id="datain" name="datain" onChange={handleChange}>
            </input>
            </label>
          </form>
          <button type="button" onClick={handleSubmit}>Check formula!</button>
        </header>
        {backendresponse=="cat"&&
        (<div dangerouslySetInnerHTML={{ __html: iframeString }} />)
        }
        {backendresponse!="cat"&&
        (<h1>{backendresponse}</h1>)
        }
      </div>
      </div>
    </div>
  );
}

export default App;
