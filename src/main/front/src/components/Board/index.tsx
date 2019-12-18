import React, {Dispatch, SetStateAction, useEffect, useState} from "react";
import "./Board.less"
import {useHistory} from "react-router-dom";
import {getParticipatingCards, getPrincipal} from "../../httpClient";
import report from "../../utils/report";
import Logout from "../LogoutButton";
import Button from "@material-ui/core/Button";
import AddIcon from '@material-ui/icons/Add';
import {CardViewModel} from "../../models/CardViewModel";
import useDocumentTitle from "../../utils/useDocumentTitle";
import CardModal from "./CardModal";

const Board: React.FC = () => {
    useDocumentTitle("Board — OnMeeting");
    const [username, setUsername] = useState("guest");
    const history = useHistory();
    const [cards, setCards] = useState([] as CardViewModel[]);
    const [openModal, setOpenModal] = useState(false);
    const checkUserAuthorized = () => {
        getPrincipal()
            .then(p => {
                if (!p.authorized) {
                    history.push("/")
                }
                else {
                    setUsername(p.username)
                }
            })
            .catch(report);
    };
    const loadCards = () => {
        checkUserAuthorized();
        getParticipatingCards().then(setCards).catch(report)
    };

    useEffect(() => {
        loadCards();
        const intervalID = window.setInterval(loadCards, 5000);
        return () => {
            window.clearInterval(intervalID);
        }
    //eslint-disable-next-line
    }, []);

    return (
        <>
            <div className="Board-container">
                <BoardHeader username={username} />
                <div className="Board-wrapper">
                    {cards.length === 0
                        ? <EmptyBoard setOpenModal={setOpenModal} />
                        : <NotEmptyBoard cards={cards}/>
                    }
                </div>
            </div>
            <CardModal isOpen={openModal} setOpen={setOpenModal}/>
        </>
    );
};

interface OpenModalProps {
    setOpenModal: Dispatch<SetStateAction<boolean>>
}

const BoardHeader: React.FC<{username: string}> = ({username}) => (
    <div className="Board-header">
        <div className="Board-greeting">{`Hello, ${username}!`}</div>
        <Logout />
    </div>
);

const EmptyBoard: React.FC<OpenModalProps> = props => (
    <div className="EmptyBoard">
        <span>You don't have any cards yet...</span>
        <AddCardButton  {...props} />
    </div>
);

const NotEmptyBoard: React.FC<{cards: CardViewModel[]}> = ({cards}) => {
    return null;
};

const AddCardButton: React.FC<OpenModalProps> = ({setOpenModal}) => (
    <Button
        variant="contained"
        color="default"
        startIcon={<AddIcon />}
        children="Add"
        onClick={() => setOpenModal(true)}
    />
);

export default Board;