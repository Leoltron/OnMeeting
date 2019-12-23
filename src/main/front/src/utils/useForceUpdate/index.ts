import { useState } from 'react'

const useForceUpdate = () => {
    const [, setState] = useState(true);
    return () => setState(s => !s)
};

export default useForceUpdate
